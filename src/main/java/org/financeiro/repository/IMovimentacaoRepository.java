package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.entity.Movimentacao;

public interface IMovimentacaoRepository {

	Movimentacao criaMovimentacao(Movimentacao movimentacao);

	Movimentacao atualizaMovimentacao(Movimentacao movimentacao);

	Movimentacao listaMovimentacaoPorId(Long id);

	Movimentacao listaMovimentacaoPorIdEConta(Long id, String googleId);

	List<Movimentacao> listaMovimentacoesPorIdContaEPeriodo(String googleId, Date dataInicio, Date dataFim);

	List<Movimentacao> listaMovimentacoesPorTipoMovimentacao(String googleId, String tipoMovimentacao, Date dataInicio,
			Date dataFim);

	List<Movimentacao> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria, Date dataInicio, Date dataFim);

	List<Movimentacao> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria);

	Boolean removeMovimentacao(Long idMovimentacao, String googleId);

}
