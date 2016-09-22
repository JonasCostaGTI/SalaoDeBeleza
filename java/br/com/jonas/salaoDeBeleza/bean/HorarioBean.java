package br.com.jonas.salaoDeBeleza.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.jonas.salaoDeBeleza.dao.ClienteDAO;
import br.com.jonas.salaoDeBeleza.domain.Agenda;
import br.com.jonas.salaoDeBeleza.domain.Cliente;
import br.com.jonas.salaoDeBeleza.domain.Servico;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HorarioBean implements Serializable {

	private ScheduleModel scheduleModel;
	private Cliente cliente;
	private List<Cliente> clientes;
	private List<Servico> servicos;
	private List<Agenda> agendas;
	private Agenda agenda;
	private Servico servico;
	private String console;

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
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

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}

	public void setScheduleModel(ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}

	@PostConstruct
	public void listar() {
		scheduleModel = new DefaultScheduleModel();

		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();

			if (clientes != null) {
				for (Cliente cliente : clientes) {
					if (cliente.getAgenda().getDataHoraInicio() != null) {
						DefaultScheduleEvent defaultScheduleEvent = new DefaultScheduleEvent();

						defaultScheduleEvent.setEndDate(cliente.getAgenda().getDataHoraInicio());
						defaultScheduleEvent.setStartDate(cliente.getAgenda().getDataHoraFim());

						String horaInicio[] = cliente.getAgenda().getDataHoraInicio().toString().split(" ");
						String horaFinal[] = cliente.getAgenda().getDataHoraFim().toString().split(" ");

						defaultScheduleEvent.setTitle(
								cliente.getNome() + " \n" + cliente.getServico().getNomeServico() + "\n" + "Horario: "
										+ horaInicio[1].substring(0, 5) + " / " + horaFinal[1].substring(0, 5));

						defaultScheduleEvent.setAllDay(false);
						defaultScheduleEvent.setEditable(true);
						scheduleModel.addEvent(defaultScheduleEvent);
					}
				}
			}

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao tentar listar os Horarios");
			e.printStackTrace();
		}

	}

	public void novo(SelectEvent evento) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clientes = clienteDAO.listar();

		cliente = new Cliente();
		agenda = new Agenda();

		agenda.setDataHoraInicio((Date) evento.getObject());

		Calendar dataInicio = Calendar.getInstance();
		dataInicio.setTime(agenda.getDataHoraInicio());
		dataInicio.add(dataInicio.DATE, 1);
		agenda.setDataHoraInicio(dataInicio.getTime());

		agenda.setDataHoraFim(agenda.getDataHoraFim());
		cliente.setAgenda(agenda);

	}

	public void salvar() {
		agenda.setDataHoraFim(agenda.getDataHoraInicio());

		Calendar dataFim = Calendar.getInstance();
		dataFim.setTime(agenda.getDataHoraFim());
		dataFim.add(dataFim.HOUR_OF_DAY, 1);
		agenda.setDataHoraFim(dataFim.getTime());

		Servico servico = new Servico();
		servico.setNomeServico(console);
		cliente.setAgenda(agenda);
		cliente.setServico(servico);

		ClienteDAO clienteDAO = new ClienteDAO();

		try {
			clienteDAO.merge(cliente);
			Messages.addGlobalInfo("Cliente salvo com sucesso");
			listar();
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar salvar o Cliente");
		}

	}

}
