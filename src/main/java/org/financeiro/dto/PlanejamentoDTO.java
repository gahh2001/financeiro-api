package org.financeiro.dto;

import java.util.Date;
import java.util.List;

import org.financeiro.entity.Planejamento;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanejamentoDTO {

	private Long id;
	private Boolean ativo;
	private Date dataInicio;
	private Date dataFim;
	private String nome;
	private String tipo;
	private String recorrencia;
	private Double valor;
	private List<Integer> categorias;
	private String googleId;

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
		if (this.categorias != null) {
			entidade.setCategorias(new Gson().toJson(this.categorias));
		}
		entidade.setGoogleId(this.googleId);
		return entidade;
	}
}
