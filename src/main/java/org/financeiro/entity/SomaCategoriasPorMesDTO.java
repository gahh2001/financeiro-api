package org.financeiro.entity;

public class SomaCategoriasPorMesDTO {

	private String nomeCategoria;
	private Double somaMovimentacao;

	public SomaCategoriasPorMesDTO(String nomeCategoria, Double somaMovimentacao) {
		this.nomeCategoria = nomeCategoria;
		this.somaMovimentacao = formatDouble(somaMovimentacao);
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public Double getSomaMovimentacao() {
		return somaMovimentacao;
	}

	private Double formatDouble(Double value) {
		String stringDouble = value.toString();
		String stringFormated = stringDouble.length() >= 5
			? stringDouble.substring(0, 5)
			: stringDouble;
		return Double.valueOf(stringFormated);
	}
}