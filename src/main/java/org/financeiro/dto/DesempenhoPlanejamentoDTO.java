package org.financeiro.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesempenhoPlanejamentoDTO {

	private Double valor;
	private Date data;

	public DesempenhoPlanejamentoDTO(Double valor, Date data) {
		this.valor = valor;
		this.data = data;
	}
}
