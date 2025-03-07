package org.financeiro.dto;

import java.util.Date;

import org.financeiro.entity.Movimentacao;

public class MovimentacaoDTO {

	private Long id;
	private String googleId;
	private Double valor;
	private Date dataMovimentacao;
	private String tipoMovimentacao;
	private Long idCategoriaMovimentacao;
	private String nomeCategoriaMovimentacao;
	private String descricaoMovimentacao;
	private String icone;
	private String corIcone;

	public MovimentacaoDTO(Movimentacao movimentacao) {
		this.id = movimentacao.getId();
		this.googleId = movimentacao.getGoogleId();
		this.valor = movimentacao.getValor();
		this.dataMovimentacao = movimentacao.getDataMovimentacao();
		this.tipoMovimentacao = movimentacao.getTipoMovimentacao();
		this.idCategoriaMovimentacao = movimentacao.getIdCategoriaMovimentacao();
		this.descricaoMovimentacao = movimentacao.getDescricaoMovimentacao();
	}

	public MovimentacaoDTO() {
	}

	public MovimentacaoDTO(Long id, Double valor, Date dataMovimentacao, String tipoMovimentacao,
			Long idCategoriaMovimentacao, String nomeCategoriaMovimentacao,
			String descricaoMovimentacao, String icone, String corIcone) {
		this.id = id;
		this.valor = valor;
		this.dataMovimentacao = dataMovimentacao;
		this.tipoMovimentacao = tipoMovimentacao;
		this.idCategoriaMovimentacao = idCategoriaMovimentacao;
		this.nomeCategoriaMovimentacao = nomeCategoriaMovimentacao;
		this.descricaoMovimentacao = descricaoMovimentacao;
		this.icone = icone;
		this.corIcone = corIcone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
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

	public String getDescricaoMovimentacao() {
		return descricaoMovimentacao;
	}

	public void setDescricaoMovimentacao(String descricaoMovimentacao) {
		this.descricaoMovimentacao = descricaoMovimentacao;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getCorIcone() {
		return corIcone;
	}

	public void setCorIcone(String corIcone) {
		this.corIcone = corIcone;
	}
}