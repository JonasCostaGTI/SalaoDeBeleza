package br.com.jonas.salaoDeBeleza.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Usuario extends Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;

	@Column(length = 50)
	private String login;

	@Column(name = "senha")
	private String senha;

	@Column(name = "Ativo")
	private Boolean ativo;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	// nao sera mapeado
	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = null;

		if (ativo) {
			ativoFormatado = "Sim";
		} else {
			ativoFormatado = "Nao";
		}

		return ativoFormatado;
	}
	
	

}
