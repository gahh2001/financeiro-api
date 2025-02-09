package org.financeiro.entity;

import java.util.Date;
import java.util.List;

import org.financeiro.dto.PlanejamentoDTO;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Planejamento extends PanacheEntity {

	@Column(columnDefinition = "DATE")
	private Date dataInicio;

	@Column(columnDefinition = "DATE")
	private Date dataFim;

	private String nome;
	private String tipo; // meta/limite
	private String recorrencia; // mensal/anual
	private Double valor;
	private String categorias;
	private String googleId;
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRecorrencia() {
		return recorrencia;
	}

	public void setRecorrencia(String recorrencia) {
		this.recorrencia = recorrencia;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public PlanejamentoDTO dto() {
		PlanejamentoDTO dto = new PlanejamentoDTO();
		dto.setId(this.id);
		dto.setAtivo(this.ativo);
		dto.setDataInicio(this.dataInicio);
		dto.setDataFim(this.dataFim);
		dto.setNome(this.nome);
		dto.setTipo(this.tipo);
		dto.setRecorrencia(this.recorrencia);
		dto.setValor(this.valor);
		if (this.categorias != null) {
			dto.setCategorias(new Gson().fromJson(this.categorias, new TypeToken<List<Integer>>() {}.getType()));
		}
		dto.setGoogleId(this.googleId);
		return dto;
	}
}
