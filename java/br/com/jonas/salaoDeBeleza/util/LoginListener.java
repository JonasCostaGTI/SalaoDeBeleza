package br.com.jonas.salaoDeBeleza.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.jonas.salaoDeBeleza.bean.LoginBean;
import br.com.jonas.salaoDeBeleza.domain.Usuario;

@SuppressWarnings("serial")
public class LoginListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();
		
		boolean ehPaginaAutenticacao = paginaAtual.contains("login.xhtml");
		
		if (!ehPaginaAutenticacao) {
			LoginBean loginBean = Faces.getSessionAttribute("loginBean");
			
			if (loginBean == null) {
				Faces.navigate("/pages/login.xhtml");
				return;
			}
			
			Usuario usuario = loginBean.getUsuarioLogado();
			if (usuario == null) {
				Faces.navigate("/pages/login.xhtml");
				return;
			}
		}
		
		
		
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
