package br.com.jonas.salaoDeBeleza.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.jonas.salaoDeBeleza.dao.FuncionarioDAO;
import br.com.jonas.salaoDeBeleza.domain.Funcionario;



@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {
	
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
	// novo
	public void novo() {
		funcionario = new Funcionario();

		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao listar Pessoas");
			e.printStackTrace();
		}
	}
	
	// salvar
	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);

			novo();
			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Funcionario salvo com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar salvar");
			e.printStackTrace();
		}
	}
	
	// listar
	@PostConstruct
	public void listar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar Funcionarios");
			e.printStackTrace();
		}
	}
	
	// excluir
	public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);
			
			funcionarios = funcionarioDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalInfo("Funcionario excluido com sucesso");
			e.printStackTrace();
		}
	}
	
	//editar
	public void editar(ActionEvent evento) {
		
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Erro ao tentar editar");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	

}
