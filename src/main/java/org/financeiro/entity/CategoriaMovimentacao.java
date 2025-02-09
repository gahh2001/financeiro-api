package org.financeiro.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class CategoriaMovimentacao extends PanacheEntity{

	private String tipoMovimentacao;
	private String nomeCategoria;
	private String googleId;
	private String icone;
	private String corIcone;

	public CategoriaMovimentacao() {
	}

	public CategoriaMovimentacao(Long id, String tipoMovimentacao, String nomeCategoria, String googleId) {
		this.id = id;
		this.tipoMovimentacao = tipoMovimentacao;
		this.nomeCategoria = nomeCategoria;
		this.googleId = googleId;
	}

	public CategoriaMovimentacao(String tipoMovimentacao, String nomeCategoria, String googleId,
			String cor, String icone) {
		this.tipoMovimentacao = tipoMovimentacao;
		this.nomeCategoria = nomeCategoria;
		this.googleId = googleId;
		this.corIcone = cor;
		this.icone = icone;
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

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String simbolo) {
		this.icone = simbolo;
	}

	public String getCorIcone() {
		return corIcone;
	}

	public void setCorIcone(String corSimbolo) {
		this.corIcone = corSimbolo;
	}
}
