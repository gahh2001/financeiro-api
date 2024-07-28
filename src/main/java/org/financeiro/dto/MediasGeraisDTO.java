package org.financeiro.dto;

public class MediasGeraisDTO {

	private String categoriaMaisGasta;
	private String categoriaMenosGasta;
	private Double ganhoMedia;
	private Double gastomedia;

	public MediasGeraisDTO( String categoriaMaisGasta, String categoriaMenosGasta,
			Double ganhoMedia, Double gastomedia ) {
		this.categoriaMaisGasta = categoriaMaisGasta;
		this.categoriaMenosGasta = categoriaMenosGasta;
		this.ganhoMedia = ganhoMedia;
		this.gastomedia = gastomedia;
	}

	public MediasGeraisDTO( String categoriaMaisGasta, Double somaMovimentacao ) {
		this.categoriaMaisGasta = categoriaMaisGasta;
	}
	public MediasGeraisDTO( Double ganhoMedia ) {
		this.ganhoMedia = ganhoMedia;
	}

	public String getCategoriaMaisGasta() {
		return categoriaMaisGasta;
	}
	public void setCategoriaMaisGasta( String categoriaMaisGasta ) {
		this.categoriaMaisGasta = categoriaMaisGasta;
	}
	public String getCategoriaMenosGasta() {
		return categoriaMenosGasta;
	}
	public void setCategoriaMenosGasta( String categoriaMenosGasta ) {
		this.categoriaMenosGasta = categoriaMenosGasta;
	}
	public Double getGanhoMedia() {
		return ganhoMedia;
	}
	public void setGanhoMedia( Double ganhoMedia ) {
		this.ganhoMedia = ganhoMedia;
	}
	public Double getGastomedia() {
		return gastomedia;
	}
	public void setGastomedia( Double gastomedia ) {
		this.gastomedia = gastomedia;
	}
}
