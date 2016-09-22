package br.com.jonas.salaoDeBeleza.daoTEST;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jonas.salaoDeBeleza.dao.UsuarioDAO;
import br.com.jonas.salaoDeBeleza.domain.Usuario;

public class UsuarioDAOTest {

	@Test
	@Ignore
	public void salvar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();

		usuario.setAtivo(true);
		usuario.setNome("Jonas");
		usuario.setLogin("jonasCosta");
		usuario.setSenha("1234");

		usuarioDAO.salvar(usuario);
		System.out.println("Usuario salvo com sucesso");
	}
}
