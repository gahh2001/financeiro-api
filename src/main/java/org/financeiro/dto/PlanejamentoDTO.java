package org.financeiro.dto;

import java.util.Date;
import java.util.List;

import org.financeiro.entity.Planejamento;

import com.google.gson.Gson;

public class PlanejamentoDTO {

	private Long id;
	private Boolean ativo;
	private Date dataInicio;
	private Date dataFim;
	private String nome;
	private String tipo;
	private String recorrencia;
	private Double valor;
	private String tipoCategorias;
	private List<Integer> categorias;
	private String googleId;

	public Long getId() {
		return id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	public String getRecorrencia() {
		return recorrencia;
	}

	public Double getValor() {
		return valor;
	}

	public String getTipoCategorias() {
		return tipoCategorias;
	}

	public List< Integer > getCategorias() {
		return categorias;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setDataInicio( Date dataInicio ) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim( Date dataFim ) {
		this.dataFim = dataFim;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public void setRecorrencia( String recorrencia ) {
		this.recorrencia = recorrencia;
	}

	public void setValor( Double valor ) {
		this.valor = valor;
	}

	public void setTipoCategorias( String tipoCategorias ) {
		this.tipoCategorias = tipoCategorias;
	}

	public void setCategorias( List< Integer > categorias ) {
		this.categorias = categorias;
	}

	public void setGoogleId( String googleId ) {
		this.googleId = googleId;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo( Boolean ativo ) {
		this.ativo = ativo;
	}

	public Planejamento entidade() {
		Planejamento entidade = new Planejamento();
		entidade.setId(this.id);
		entidade.setAtivo(this.ativo);
		entidade.setDataInicio(this.dataInicio);
		entidade.setDataFim(this.dataFim);
		entidade.setNome(this.nome);
		entidade.setTipo(this.tipo);
		entidade.setRecorrencia(this.recorrencia);
		entidade.setValor(this.valor);
		entidade.setTipoCategorias(this.tipoCategorias);
		if (this.categorias != null) {
			entidade.setCategorias(new Gson().toJson(this.categorias));
		}
		entidade.setGoogleId(this.googleId);
		return entidade;
	}
}
