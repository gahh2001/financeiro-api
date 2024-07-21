package org.financeiro.dto;

import java.util.Date;

public class SomaCategoriasPorPeriodoDTO {

	private String nomeCategoria;
	private Double somaMovimentacao;
	private Date data;

	public SomaCategoriasPorPeriodoDTO(String nomeCategoria, Double somaMovimentacao) {
		this.nomeCategoria = nomeCategoria;
		this.somaMovimentacao = formatDouble(somaMovimentacao);
	}

	public SomaCategoriasPorPeriodoDTO( String nomeCategoria,
			Date data, Double somaMovimentacao ) {
		this.nomeCategoria = nomeCategoria;
		this.somaMovimentacao = somaMovimentacao;
		this.data = data;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public Double getSomaMovimentacao() {
		return somaMovimentacao;
	}

	public Date getData() {
		return data;
	}

	public void setNomeCategoria( String nomeCategoria ) {
		this.nomeCategoria = nomeCategoria;
	}

	private Double formatDouble(Double value) {
		String stringDouble = value.toString();
		String stringFormated = stringDouble.length() >= 5
			? stringDouble.substring(0, 5)
			: stringDouble;
		return Double.valueOf(stringFormated);
	}
}