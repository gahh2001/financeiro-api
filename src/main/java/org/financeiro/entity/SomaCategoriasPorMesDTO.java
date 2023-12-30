package org.financeiro.entity;

public class SomaCategoriasPorMesDTO {

	private String nomeCategoria;
	private Double somaMovimentacao;
	private String tipoMovimentacao;

	public SomaCategoriasPorMesDTO(String nomeCategoria, Double somaMovimentacao, String tipoMovimentacao) {
		this.nomeCategoria = nomeCategoria;
		this.somaMovimentacao = formatDouble(somaMovimentacao);
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public Double getSomaMovimentacao() {
		return somaMovimentacao;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	private Double formatDouble(Double value) {
		String stringDouble = value.toString();
		String stringFormated = stringDouble.length() >= 5
				? stringDouble.substring(0, 5)
				: stringDouble;
		return Double.valueOf(stringFormated);
	}
}