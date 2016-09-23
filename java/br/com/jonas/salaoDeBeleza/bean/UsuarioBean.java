package br.com.jonas.salaoDeBeleza.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.jonas.salaoDeBeleza.dao.UsuarioDAO;
import br.com.jonas.salaoDeBeleza.domain.Usuario;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	private List<Usuario> usuarios;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public void novo() {
		try {
			usuario = new Usuario();


		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar pessoas");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {


			 UsuarioDAO usuarioDAO = new UsuarioDAO();
			 usuarios = usuarioDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar Usuario");
			e.printStackTrace();
		}
	}

	public List<Usuario> listarComRetorno() {
		try {


			 UsuarioDAO usuarioDAO = new UsuarioDAO();
			 usuarios = usuarioDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar Usuario");
			e.printStackTrace();
		}

		return usuarios;
	}

	public void salvar() {

		try {
		
			usuarios = listarComRetorno();
			
			SimpleHash simpleHash = new SimpleHash("md5",usuario.getSenha());
			usuario.setSenha(simpleHash.toHex());

			 UsuarioDAO usuarioDAO = new UsuarioDAO();
			 usuarioDAO.merge(usuario);
			 
			 listar();

			Messages.addGlobalInfo("Usuario Armazenado com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar salvar Usuario");
			e.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
	}

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			
			usuarios = listarComRetorno();

			 UsuarioDAO usuarioDAO = new UsuarioDAO();
			 usuarioDAO.excluir(usuario);

			usuarios = listarComRetorno();
			Messages.addGlobalInfo("Usuario Excluido com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir um usuario");
			e.printStackTrace();
		}
	}

}
