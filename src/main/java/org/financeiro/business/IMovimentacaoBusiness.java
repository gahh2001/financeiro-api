package org.financeiro.business;

import java.util.Date;
import java.util.List;
import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.entity.Movimentacao;

public interface IMovimentacaoBusiness {

	Movimentacao criaMovimentacao(Movimentacao movimentacao);

	Movimentacao atualizaMovimentacao(Movimentacao movimentacao);

	List<MovimentacaoDTO> listaMovimentacoesPorIdContaEPeriodo(String googleId, Date dataInicio, Date dataFim);

	List<Movimentacao> listaMovimentacoesPorTipoMovimentacao(String googleId, String tipoMovimentacao,
			String dataInicio, String dataFim);

	List<Movimentacao> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria, String dataInicio,
			String dataFim);

	List<Movimentacao> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria);

	Boolean removeMovimentacao(Long idMovimentacao, String googleId);

}
