package org.financeiro.dto;

public class CategoriaMovimentacaoDTO {
	
	private Long idCategoriaMovimentacao;
	private String tipoMovimentacao;
	private String nomeCategoria;
	private Long idConta;

	public CategoriaMovimentacaoDTO() {
	}

	public CategoriaMovimentacaoDTO(Long idCategoriaMovimentacao, String tipoMovimentacao, String nomeCategoria,
			Long idConta) {
		this.idCategoriaMovimentacao = idCategoriaMovimentacao;
		this.tipoMovimentacao = tipoMovimentacao;
		this.nomeCategoria = nomeCategoria;
		this.idConta = idConta;
	}

	public Long getIdCategoriaMovimentacao() {
		return idCategoriaMovimentacao;
	}

	public void setIdCategoriaMovimentacao(Long idCategoriaMovimentacao) {
		this.idCategoriaMovimentacao = idCategoriaMovimentacao;
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

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
}