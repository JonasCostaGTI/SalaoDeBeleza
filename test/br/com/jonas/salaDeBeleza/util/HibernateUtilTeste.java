package br.com.jonas.salaDeBeleza.util;

import org.hibernate.Session;
import org.junit.Test;

import br.com.jonas.salaoDeBeleza.util.HibernateUtil;

public class HibernateUtilTeste {

	@Test
	public void conectar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
