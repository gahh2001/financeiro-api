package org.financeiro.business;

import java.util.List;

import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.entity.Movimentacao;
import org.financeiro.exceptions.NonExistentAccount;

public interface IMovimentacaoBusiness {

	Movimentacao criaMovimentacao(Movimentacao movimentacao) throws NonExistentAccount;

	Movimentacao atualizaMovimentacao(Movimentacao movimentacao) throws NonExistentAccount;

	List<MovimentacaoDTO> listaMovimentacoesPorIdContaEPeriodo(String googleId, Long dataInicio, Long dataFim);

	List<MovimentacaoDTO> listaMovimentacoesPorParametros(String googleId, Long dataInicio, Long dataFim,
		String tipo, String categorias);

	List<MovimentacaoDTO> listaMovimentacoesPorTipoMovimentacao(String googleId, String tipoMovimentacao,
			String dataInicio, String dataFim);

	List<MovimentacaoDTO> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria, String dataInicio,
			String dataFim);

	List<Movimentacao> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria);

	Boolean removeMovimentacao(Long idMovimentacao, String googleId);

}
