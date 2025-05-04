package org.financeiro.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CategoriaMovimentacao extends PanacheEntity{

	private String tipoMovimentacao;
	private String nomeCategoria;
	private String googleId;
	private String icone;
	private String corIcone;
	private Double valorPadrao;

	public CategoriaMovimentacao(Long id, String tipoMovimentacao, String nomeCategoria, String googleId) {
		this.id = id;
		this.tipoMovimentacao = tipoMovimentacao;
		this.nomeCategoria = nomeCategoria;
		this.googleId = googleId;
	}

	public CategoriaMovimentacao(String tipoMovimentacao, String nomeCategoria, String googleId,
			String cor, String icone) {
		this.tipoMovimentacao = tipoMovimentacao;
		this.nomeCategoria = nomeCategoria;
		this.googleId = googleId;
		this.corIcone = cor;
		this.icone = icone;
	}

	public Long getId() {
		return id;
	}
}