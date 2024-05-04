package org.financeiro.entity;

import jakarta.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class CategoriaMovimentacao extends PanacheEntity{

	private Long id;
	private String tipoMovimentacao;
	private String nomeCategoria;
	private String googleId;

	public CategoriaMovimentacao() {
	}
	
	public CategoriaMovimentacao(Long id, String tipoMovimentacao, String nomeCategoria, String googleId) {
		this.id = id;
		this.tipoMovimentacao = tipoMovimentacao;
		this.nomeCategoria = nomeCategoria;
		this.googleId = googleId;
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

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId( String googleId ) {
		this.googleId = googleId;
	}
}
