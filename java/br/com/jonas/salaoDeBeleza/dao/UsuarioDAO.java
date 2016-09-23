package br.com.jonas.salaoDeBeleza.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.jonas.salaoDeBeleza.domain.Usuario;
import br.com.jonas.salaoDeBeleza.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
public Usuario autenticar(String senha, String login){
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("usuaria", "u");
			
			consulta.add(Restrictions.eq("u.login", login));
			
			//criptografa
			SimpleHash simpleHash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", simpleHash.toHex()));
			
			Usuario usuario = (Usuario) consulta.uniqueResult();
			
			return usuario;

		} catch (RuntimeException erro) {
			
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
