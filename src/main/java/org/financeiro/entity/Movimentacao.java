package org.financeiro.entity;

import java.util.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Movimentacao extends PanacheEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "DATE")
	private Date dataMovimentacao;
	
	private Double valor;
	private String tipoMovimentacao;
	private Long idCategoriaMovimentacao;
	private String descricaoMovimentacao;
	private String googleId;

	public Movimentacao() {
	}

	public Long getId() {
		return id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
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

	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public String getDescricaoMovimentacao() {
		return descricaoMovimentacao;
	}

	public void setDescricaoMovimentacao(String descricaoMovimentacao) {
		this.descricaoMovimentacao = descricaoMovimentacao;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId( String googleId ) {
		this.googleId = googleId;
	}
}