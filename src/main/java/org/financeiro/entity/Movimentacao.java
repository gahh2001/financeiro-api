package org.financeiro.entity;

import java.util.Date;

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
public class Movimentacao extends PanacheEntity {

	@Column(columnDefinition = "DATE")
	private Date dataMovimentacao;

	private Double valor;
	private String tipoMovimentacao;
	private Long idCategoriaMovimentacao;
	private String descricaoMovimentacao;
	private String googleId;
	private Boolean alteraSaldo;

	public Long getId() {
		return id;
	}
}