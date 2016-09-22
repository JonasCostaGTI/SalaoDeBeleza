package br.com.jonas.salaoDeBeleza.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Funcionario extends Pessoa  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
 	
	@Column(length = 50)
	private String funcao;
	
	@Column(length = 15, unique = true)
	private String carteiraTrabalho;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getCarteiraTrabalho() {
		return carteiraTrabalho;
	}

	public void setCarteiraTrabalho(String carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	
	
	
	
	
}
