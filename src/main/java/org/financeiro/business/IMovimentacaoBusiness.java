package org.financeiro.business;

import java.util.Date;
import java.util.List;

import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.entity.Movimentacao;

public interface IMovimentacaoBusiness {

	Movimentacao criaMovimentacao(Movimentacao movimentacao);

	Movimentacao listaMovimentacaoPorId(Long id);

	List<MovimentacaoDTO> listaMovimentacaosPorIdContaEPeriodo(Long idConta, Date dataInicio, Date dataFim);

	List<Movimentacao> listaMovimentacaosPorTipoMovimentacao(Long idConta, String tipoMovimentacao,
			String dataInicio, String dataFim);

	List<Movimentacao> listaMovimentacaoPorIdCategoria(Long idConta, Long idCategoria, String dataInicio,
			String dataFim);

	List<Movimentacao> listaMovimentacaoPorIdCategoria(Long idConta, Long idCategoria);

	void removeMovimentacao(Long idMovimentacao);

}
