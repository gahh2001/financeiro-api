package org.financeiro.dto;

import java.util.Date;

import org.financeiro.entity.Movimentacao;

public class MovimentacaoDTO {

	private Long id;
	private Long idConta;
	private Double valor;
	private Date dataMovimentacao;
	private String tipoMovimentacao;
	private Long idCategoriaMovimentacao;
	private String nomeCategoriaMovimentacao;

	public MovimentacaoDTO(Movimentacao movimentacao) {
		this.id = movimentacao.getId();
		this.idConta = movimentacao.getIdConta();
		this.valor = movimentacao.getValor();
		this.dataMovimentacao = movimentacao.getDataMovimentacao();
		this.tipoMovimentacao = movimentacao.getTipoMovimentacao();
		this.idCategoriaMovimentacao = movimentacao.getIdCategoriaMovimentacao();
	}

	public MovimentacaoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
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

	public String getNomeCategoriaMovimentacao() {
		return nomeCategoriaMovimentacao;
	}

	public void setNomeCategoriaMovimentacao(String nomeCategoriaMovimentacao) {
		this.nomeCategoriaMovimentacao = nomeCategoriaMovimentacao;
	}
}