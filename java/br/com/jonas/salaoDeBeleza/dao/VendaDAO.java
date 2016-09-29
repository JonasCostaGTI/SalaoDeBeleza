package br.com.jonas.salaoDeBeleza.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jonas.salaoDeBeleza.domain.ItemVenda;
import br.com.jonas.salaoDeBeleza.domain.Produto;
import br.com.jonas.salaoDeBeleza.domain.Venda;
import br.com.jonas.salaoDeBeleza.util.HibernateUtil;



public class VendaDAO extends GenericDAO<Venda> {
	public void salvar(Venda venda, List<ItemVenda> itensVenda){
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			
			sessao.save(venda);
			
			for (int i = 0; i < itensVenda.size(); i++) {
				ItemVenda itemVenda = itensVenda.get(i);
				itemVenda.setVenda(venda);
				
				sessao.save(itemVenda);
				
				
				Produto produto = itemVenda.getProduto();
				
				int quantidade = produto.getQuantidade() - itemVenda.getQuantidade();
				
				if(quantidade >= 0){
					produto.setQuantidade(new Short((produto.getQuantidade() - itemVenda.getQuantidade()) + ""));
					
					sessao.update(produto);
				}else{
					//joga este erro para a camada Bean
					throw new RuntimeException("Quantidade insuficiente em estoque");
				}
				
			}
			
			transacao.commit();

		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
