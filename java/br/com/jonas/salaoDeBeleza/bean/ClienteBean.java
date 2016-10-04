package br.com.jonas.salaoDeBeleza.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.jonas.salaoDeBeleza.dao.ClienteDAO;
import br.com.jonas.salaoDeBeleza.domain.Agenda;
import br.com.jonas.salaoDeBeleza.domain.Cliente;
import br.com.jonas.salaoDeBeleza.domain.Servico;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	
	private Cliente cliente;
	private List<Cliente> clientes;
	private Agenda agenda;
	private List<Servico> servicos;
	private Servico servico;
	
	public ClienteBean() {
		// TODO Auto-generated constructor stub

		cliente = new Cliente();
		
		
	}

	
	
	public List<Servico> getServicos() {
		return servicos;
	}
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	@PostConstruct
	public void listar(){
		ClienteDAO clienteDAO = new ClienteDAO();
		clientes = clienteDAO.listar();
	}
	
	public void novo() {
		try {
			
			cliente = new Cliente();
	
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar clientes");
			e.printStackTrace();
		}

	}
	
	
	public void salvar() {
		try {
		
			//System.out.println(cliente.getAgenda().getHoraInicial());
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);
			
			novo();
			clientes = clienteDAO.listar();
			Messages.addFlashGlobalInfo("Cliente Armazenado com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar armazenar cliente");
			e.printStackTrace();
		}
	}
	
	//editar
	public void editar(ActionEvent evento) {
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
		
	}
	
	//excluir
	public void excluir(ActionEvent evento) {
		try {
			cliente =  (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);
			
			clientes = clienteDAO.listar();
			Messages.addFlashGlobalInfo("Cliente excluido com sucesso");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Erro ao tentar excluir cliente");
			e.printStackTrace();
		}
	}
	
	
	

}
