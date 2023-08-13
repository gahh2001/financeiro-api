package org.financeiro.entity;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Movimentacao extends PanacheEntity{

	private Long id;
	private Long idConta;
	private Double valor;
	private Long dataMovimentacao;
	private String tipoMovimentacao;
	private Long idCategoriaMovimentacao;
	
	public Movimentacao() {
	}

	public Movimentacao(Long id, Long idConta, Double valor, Long dataMovimentacao, String tipoMovimentacao,
			Long idCategoriaMovimentacao) {
		this.id = id;
		this.idConta = idConta;
		this.valor = valor;
		this.dataMovimentacao = dataMovimentacao;
		this.tipoMovimentacao = tipoMovimentacao;
		this.idCategoriaMovimentacao = idCategoriaMovimentacao;
	}

	public Long getIdConta() {
		return idConta;
	}
	public void setIdConta(Long usuario) {
		this.idConta = usuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long idMovimentacao) {
		this.id = idMovimentacao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Long getDataMovimentacao() {
		return dataMovimentacao;
	}
	public void setDataMovimentacao(Long dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Long getIdCategoriaMovimentacao() {
		return idCategoriaMovimentacao;
	}

	public void setIdCategoriaMovimentacao(Long idCategoriaMovimentacao) {
		this.idCategoriaMovimentacao = idCategoriaMovimentacao;
	}

	

}
