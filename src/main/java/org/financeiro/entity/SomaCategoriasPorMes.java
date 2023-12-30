package org.financeiro.entity;

import java.math.BigDecimal;

public class SomaCategoriasPorMes {

	private String nomeCategoria;
	private BigDecimal somaMovimentacao;

	public SomaCategoriasPorMes(String nomeCategoria, BigDecimal somaMovimentacao) {
		this.nomeCategoria = nomeCategoria;
		this.somaMovimentacao = somaMovimentacao;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public BigDecimal getSomaMovimentacao() {
		return somaMovimentacao;
	}
}