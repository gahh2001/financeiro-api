package org.financeiro.business;

import java.util.List;

import org.financeiro.entity.Movimentacao;

public interface IMovimentacaoBusiness {
	
	Movimentacao criaMovimentacao(Movimentacao movimentacao);
	Movimentacao listaMovimentacaoPorId(Long id);
	List<Movimentacao> listaMovimentacaosPorIdConta(Long idConta, Long dataInicio, Long dataFim);
	List<Movimentacao> listaMovimentacaosPorTipoMovimentacao(Long idConta, String tipoMovimentacao, Long dataInicio, Long dataFim);
	List<Movimentacao> listaMovimentacaoPorIdCategoria(Long idConta, Long idCategoria, Long dataInicio, Long dataFim);
	List<Movimentacao> listaMovimentacaoPorIdCategoria(Long idConta, Long idCategoria);
	void removeMovimentacao(Long idMovimentacao);

}
