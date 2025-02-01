package org.financeiro.dto;

import java.util.Date;

public class DesempenhoPlanejamentoDTO {

	private Double valor;
	private Date data;

	public DesempenhoPlanejamentoDTO(Double valor, Date data) {
		this.valor = valor;
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
