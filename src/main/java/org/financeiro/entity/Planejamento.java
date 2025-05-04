package org.financeiro.entity;

import java.util.Date;
import java.util.List;

import org.financeiro.dto.PlanejamentoDTO;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
