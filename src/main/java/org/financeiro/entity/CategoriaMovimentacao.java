package org.financeiro.entity;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class CategoriaMovimentacao extends PanacheEntity{

	private Long id;
	private String tipoMovimentacao;
	private String nomeCategoria;
	private Long idConta;
	
	public CategoriaMovimentacao() {
	}
	
	public CategoriaMovimentacao(Long id, String tipoMovimentacao, String nomeCategoria, Long idConta) {
		this.id = id;
		this.tipoMovimentacao = tipoMovimentacao;
		this.nomeCategoria = nomeCategoria;
		this.idConta = idConta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idconta) {
		this.idConta = idconta;
	}

	
}
