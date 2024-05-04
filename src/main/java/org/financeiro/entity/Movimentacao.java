package org.financeiro.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import org.financeiro.dto.MovimentacaoDTO;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Movimentacao extends PanacheEntity {

	private Long id;
	private Long idConta;
	private Double valor;
	@Column(columnDefinition = "DATE")
	private Date dataMovimentacao;
	private String tipoMovimentacao;
	private Long idCategoriaMovimentacao;
	private String descricaoMovimentacao;

	public Movimentacao() {
	}

	public Movimentacao(MovimentacaoDTO movimentacaoDTO) {
		this.id = movimentacaoDTO.getId();
		this.idConta = movimentacaoDTO.getIdConta();
		this.valor = movimentacaoDTO.getValor();
		this.dataMovimentacao = movimentacaoDTO.getDataMovimentacao();
		this.tipoMovimentacao = movimentacaoDTO.getTipoMovimentacao();
		this.idCategoriaMovimentacao = movimentacaoDTO.getIdCategoriaMovimentacao();
		this.descricaoMovimentacao = movimentacaoDTO.getDescricaoMovimentacao();
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
}