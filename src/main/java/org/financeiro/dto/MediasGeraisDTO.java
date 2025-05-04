package org.financeiro.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediasGeraisDTO {

	private String categoriaMaisGasta;
	private String categoriaMenosGasta;
	private Double ganhoMedia;
	private Double gastomedia;

	public MediasGeraisDTO(String categoriaMaisGasta, String categoriaMenosGasta,
			Double ganhoMedia, Double gastomedia) {
		this.categoriaMaisGasta = categoriaMaisGasta;
		this.categoriaMenosGasta = categoriaMenosGasta;
		this.ganhoMedia = ganhoMedia;
		this.gastomedia = gastomedia;
	}

	public MediasGeraisDTO(String categoriaMaisGasta, Double somaMovimentacao) {
		this.categoriaMaisGasta = categoriaMaisGasta;
	}
	public MediasGeraisDTO(Double ganhoMedia) {
		this.ganhoMedia = ganhoMedia;
	}
}
