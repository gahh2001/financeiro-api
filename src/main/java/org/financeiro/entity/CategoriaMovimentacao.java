package org.financeiro.entity;

import jakarta.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class CategoriaMovimentacao extends PanacheEntity{

	private Long id;
	private String tipoMovimentacao;
	private String nomeCategoria;
	private String googleId;
	private String simbolo;
	private String corSimbolo;

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

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo( String simbolo ) {
		this.simbolo = simbolo;
	}

	public String getCorSimbolo() {
		return corSimbolo;
	}

	public void setCorSimbolo( String corSimbolo ) {
		this.corSimbolo = corSimbolo;
	}
}
