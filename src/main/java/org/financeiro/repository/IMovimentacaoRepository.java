package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.entity.Movimentacao;

public interface IMovimentacaoRepository {

	Movimentacao criaMovimentacao(Movimentacao movimentacao);
	Movimentacao listaMovimentacaoPorId(Long id);
	List<Movimentacao> listaMovimentacaosPorIdConta(Long idUsuario, Date dataInicio, Date dataFim);
	List<Movimentacao> listaMovimentacaosPorTipoMovimentacao(Long idConta, String tipoMovimentacao, Date dataInicio, Date dataFim);
	List<Movimentacao> listaMovimentacaoPorIdCategoria(Long idConta, Long idCategoria, Date dataInicio, Date dataFim);
	List<Movimentacao> listaMovimentacaoPorIdCategoria(Long idConta, Long idCategoria);
	void removeMovimentacao(Long idMovimentacao);

}
