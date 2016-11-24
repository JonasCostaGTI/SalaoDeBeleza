package br.com.jonas.salaoDeBeleza.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;





@Entity
public class ItemVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@Column(nullable = false)
	private Short quantidade;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valorParcial;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(nullable = false)
	private Produto produto;
	
	
	// e agora? Venda ou Funcionario?
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(nullable = false)
	private Venda venda;


	public Short getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}


	public BigDecimal getValorParcial() {
		return valorParcial;
	}


	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Venda getVenda() {
		return venda;
	}


	public void setVenda(Venda venda) {
		this.venda = venda;
	}


	public long getCodigo() {
		return codigo;
	}


	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	

}
