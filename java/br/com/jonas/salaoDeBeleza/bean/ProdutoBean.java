package br.com.jonas.salaoDeBeleza.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import br.com.jonas.salaoDeBeleza.dao.FabricanteDAO;
import br.com.jonas.salaoDeBeleza.dao.ProdutoDAO;
import br.com.jonas.salaoDeBeleza.domain.Fabricante;
import br.com.jonas.salaoDeBeleza.domain.Produto;
import br.com.jonas.salaoDeBeleza.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	// novo
	public void novo() {
		produto = new Produto();

		try {

			FabricanteBean fabricanteBean = new FabricanteBean();
			fabricantes = fabricanteBean.listarComRetorno();

			// FabricanteDAO fabricanteDAO = new FabricanteDAO();
			// fabricantes = fabricanteDAO.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar Fabricantes");
			e.printStackTrace();
		}
	}

	// listar
	@PostConstruct
	public void listar() {
		try {

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar Produtos");
			e.printStackTrace();
		}
	}

	/*
	 * Listar com retorno return Produto
	 */

	public List<Produto> listarComRetorno() {
		try {

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar Produtos");
			e.printStackTrace();
		}

		return produtos;
	}

	// salvar
	public void salvar() {
		try {

			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produtoRetorno = produtoDAO.merge(produto);

			novo();
			produtos = listarComRetorno();

			FabricanteBean fabricanteBean = new FabricanteBean();
			fabricantes = fabricanteBean.listarComRetorno();

			Messages.addFlashGlobalInfo("Produto salvo com sucesso");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Erro ao tentar salvar");
			e.printStackTrace();
		}
	}

	// editar
	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar("descricao");

			FabricanteBean fabricanteBean = new FabricanteBean();
			fabricantes = fabricanteBean.listarComRetorno();

		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Erro ao tentar Listar Fabricantes");
			e.printStackTrace();
		}
	}

	// excluir
	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);

			produtos = listarComRetorno();

			Messages.addFlashGlobalInfo("Produto excluido com sucesso");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Erro ao tentar excluir o Produto");
			e.printStackTrace();
		}

	}

	public void imprimir() {

		try {
			// idFormulario:idComponente
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();

			String produtoDescricao = (String) filtros.get("descricao");
			String fabricanteNome = (String) filtros.get("fabricante.nome");
			String fabricanteDescricao = (String) filtros.get("fabricante.decricao");

			String caminho = Faces.getRealPath("/reports/produtos.jasper");
			Map<String, Object> parametros = new HashMap<>();

			 String imagePath =
			 FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/natalia_coelho.png");
			
			 parametros.put("LOGO", imagePath);

			if (produtoDescricao == null) {
				parametros.put("PRODUTO_DESCRICAO", "%%");
			} else {
				// parametros no jasper.jrxml
				parametros.put("PRODUTO_DESCRICAO", "%" + produtoDescricao + "%");
			}

			if (fabricanteDescricao == null) {
				parametros.put("FABRICANTE_DESCRICAO", "%%");
			} else {
				parametros.put("FABRICANTE_DESCRICAO", "%" + fabricanteDescricao + "%");
			}

			if (fabricanteNome == null) {
				parametros.put("FABRICANTE_NOME", "%%");
			} else {
				parametros.put("FABRICANTE_NOME", "%" + fabricanteNome + "%");
			}

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);

			JasperPrintManager.printReport(relatorio, true);

		} catch (JRException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar relatorio");
			e.printStackTrace();
		}

	}

}
