package br.com.jonas.salaoDeBeleza.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.jonas.salaoDeBeleza.dao.UsuarioDAO;
import br.com.jonas.salaoDeBeleza.domain.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean {
	
	private Usuario usuario;
	private Usuario usuarioLogado;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
	@PostConstruct
	public void iniciar(){
		usuario = new Usuario();
	}
	
	
	public void autenticar(){
		try {
				
			UsuarioDAO usuarioDAO = new UsuarioDAO();
		    usuarioLogado = usuarioDAO.autenticar(usuario.getSenha(), usuario.getLogin());
		
		    
		    if (usuarioLogado == null || usuarioLogado.getAtivo() == false) {
				Messages.addGlobalError("Usuario ou Senha Invalidos");
				return;
			}
		    
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException e) {
			Messages.addGlobalError("Ocorreu algum erro ao tentar redirecionar");
			e.printStackTrace();
		}
	
	

}
}
