package org.financeiro.dto;

import java.util.Date;

import org.financeiro.entity.Movimentacao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovimentacaoDTO {

	private Long id;
	private String googleId;
	private Double valor;
	private Date dataMovimentacao;
	private String tipoMovimentacao;
	private Long idCategoriaMovimentacao;
	private String nomeCategoriaMovimentacao;
	private String descricaoMovimentacao;
	private String icone;
	private String corIcone;

	public MovimentacaoDTO(Movimentacao movimentacao) {
		this.id = movimentacao.getId();
		this.googleId = movimentacao.getGoogleId();
		this.valor = movimentacao.getValor();
		this.dataMovimentacao = movimentacao.getDataMovimentacao();
		this.tipoMovimentacao = movimentacao.getTipoMovimentacao();
		this.idCategoriaMovimentacao = movimentacao.getIdCategoriaMovimentacao();
		this.descricaoMovimentacao = movimentacao.getDescricaoMovimentacao();
	}

	public MovimentacaoDTO(Long id, Double valor, Date dataMovimentacao, String tipoMovimentacao,
			Long idCategoriaMovimentacao, String nomeCategoriaMovimentacao,
			String descricaoMovimentacao, String icone, String corIcone) {
		this.id = id;
		this.valor = valor;
		this.dataMovimentacao = dataMovimentacao;
		this.tipoMovimentacao = tipoMovimentacao;
		this.idCategoriaMovimentacao = idCategoriaMovimentacao;
		this.nomeCategoriaMovimentacao = nomeCategoriaMovimentacao;
		this.descricaoMovimentacao = descricaoMovimentacao;
		this.icone = icone;
		this.corIcone = corIcone;
	}
}