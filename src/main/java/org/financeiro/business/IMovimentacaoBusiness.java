package org.financeiro.business;

import java.util.List;

import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.entity.Movimentacao;
import org.financeiro.exceptions.NonExistentAccount;

public interface IMovimentacaoBusiness {

	Movimentacao criaMovimentacao(String token, Movimentacao movimentacao) throws NonExistentAccount;

	Movimentacao atualizaMovimentacao(String token, Movimentacao movimentacao) throws NonExistentAccount;

	List<MovimentacaoDTO> listaMovimentacoesPorIdContaEPeriodo(String token, Long dataInicio, Long dataFim);

	List<MovimentacaoDTO> listaMovimentacoesPorParametros(String token, Long dataInicio, Long dataFim,
		String tipo, String categorias);

	List<MovimentacaoDTO> listaMovimentacoesPorTipoMovimentacao(String token, String tipoMovimentacao,
			String dataInicio, String dataFim);

	List<MovimentacaoDTO> listaMovimentacaoPorIdCategoria(String token, Long idCategoria, String dataInicio,
			String dataFim);

	List<Movimentacao> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria);

	Boolean removeMovimentacao(Long idMovimentacao, String token);

}
