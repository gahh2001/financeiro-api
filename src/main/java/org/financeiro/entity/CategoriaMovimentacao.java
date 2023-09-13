package org.financeiro.entity;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class CategoriaMovimentacao extends PanacheEntity{

	private String tipoMovimentacao;
	private String nomeCategoria;
	private Long idConta;
	
	public CategoriaMovimentacao() {
	}
	
	public CategoriaMovimentacao(String tipoMovimentacao, String nomeCategoria, Long idConta) {
		this.tipoMovimentacao = tipoMovimentacao;
		this.nomeCategoria = nomeCategoria;
		this.idConta = idConta;
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
