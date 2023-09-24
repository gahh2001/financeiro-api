package org.financeiro.dto;

public class MovimentacaoDTO {
	
	private Long id;
	private Long idConta;
	private Double valor;
	private Long dataMovimentacao;
	private String tipoMovimentacao;
	private Long idCategoriaMovimentacao;
	
	public MovimentacaoDTO() {
	}

	public MovimentacaoDTO(Long id, Long idConta, Double valor, Long dataMovimentacao, String tipoMovimentacao,
			Long idCategoriaMovimentacao) {
		this.id = id;
		this.idConta = idConta;
		this.valor = valor;
		this.dataMovimentacao = dataMovimentacao;
		this.tipoMovimentacao = tipoMovimentacao;
		this.idCategoriaMovimentacao = idCategoriaMovimentacao;
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

	public Long getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Long dataMovimentacao) {
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
}