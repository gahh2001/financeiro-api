package org.financeiro.entity;

public class SomaCategoriasPorPeriodoDTO {

	private String nomeCategoria;
	private Double somaMovimentacao;
	private Integer mes;
	private Integer ano;

	public SomaCategoriasPorPeriodoDTO(String nomeCategoria, Double somaMovimentacao) {
		this.nomeCategoria = nomeCategoria;
		this.somaMovimentacao = formatDouble(somaMovimentacao);
	}

	public SomaCategoriasPorPeriodoDTO( String nomeCategoria, Integer mes,
			Integer ano, Double somaMovimentacao ) {
		this.nomeCategoria = nomeCategoria;
		this.somaMovimentacao = somaMovimentacao;
		this.mes = mes;
		this.ano = ano;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public Double getSomaMovimentacao() {
		return somaMovimentacao;
	}

	public Integer getMes() {
		return mes;
	}

	public Integer getAno() {
		return ano;
	}

	private Double formatDouble(Double value) {
		String stringDouble = value.toString();
		String stringFormated = stringDouble.length() >= 5
			? stringDouble.substring(0, 5)
			: stringDouble;
		return Double.valueOf(stringFormated);
	}
}