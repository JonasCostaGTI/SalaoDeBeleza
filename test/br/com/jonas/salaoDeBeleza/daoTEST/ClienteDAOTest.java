package br.com.jonas.salaoDeBeleza.daoTEST;

import java.text.ParseException;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.jonas.salaoDeBeleza.dao.ClienteDAO;
import br.com.jonas.salaoDeBeleza.domain.Agenda;
import br.com.jonas.salaoDeBeleza.domain.Cliente;
import br.com.jonas.salaoDeBeleza.domain.Servico;

public class ClienteDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		ClienteDAO clienteDAO = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		Agenda agenda = new Agenda();
		Servico servico = new Servico();

		cliente.setNome("Jonas");
		cliente.setCpf("8378329434");
		cliente.setTelefone("987475435");

//		agenda.setHoraInicial(new Date());
//		agenda.setHoraFinal(new Date());
//		agenda.setDataAtendimento(new SimpleDateFormat("dd/MM/yyyy").parse("05/09/2016"));
		
		servico.setNomeServico("Maquiagem");
		
		
		
		cliente.setAgenda(agenda);
		cliente.setServico(servico);
		
		clienteDAO.salvar(cliente);
	
		
		
	
		System.out.println("Usuario salvo com sucesso");
	

}
	
}
