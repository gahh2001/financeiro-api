package org.financeiro.business;

import java.util.List;
import org.financeiro.dto.CategoriaMovimentacaoDTO;
import org.financeiro.entity.CategoriaMovimentacao;
import org.financeiro.entity.SomaCategoriasPorPeriodoDTO;


public interface ICategoriaMovimentacaoBusiness {

	CategoriaMovimentacao criaCategoriaMovimentacao(CategoriaMovimentacao categoria);

	CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria);

	CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(String novoNome, Long idCategoria);

	CategoriaMovimentacao removeCategoriaMovimentacao(String googleId, Long idCategoria);

	List<CategoriaMovimentacaoDTO> listaCategoriasMovimentacao(String googleId);

	List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao, String googleId);

	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoria(String googleId, Long dataInicio,
		Long dataFim, String tipoMovimentacao);
	
	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoriaEMeses(String googleId, Long dataInicio,
		Long dataFim, String tipoMovimentacao);
}
