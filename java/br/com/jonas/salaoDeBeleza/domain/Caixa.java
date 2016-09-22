package br.com.jonas.salaoDeBeleza.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Caixa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;

	@Column(unique = true)
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dataFechamento;
	
	@Column(precision = 7, scale = 2)
	private BigDecimal valorAbertura;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public BigDecimal getValorAbertura() {
		return valorAbertura;
	}

	public void setValorAbertura(BigDecimal valorAbertura) {
		this.valorAbertura = valorAbertura;
	}

	
	
}
