package org.financeiro.business;

import java.util.List;

import org.financeiro.dto.SomaCategoriasPorPeriodoDTO;
import org.financeiro.entity.CategoriaMovimentacao;
import org.financeiro.exceptions.NonExistentAccount;

public interface ICategoriaMovimentacaoBusiness {

	CategoriaMovimentacao criaCategoriaMovimentacao(CategoriaMovimentacao categoria) throws NonExistentAccount;

	CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria, String googleId);

	CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(String novoNome, Long idCategoria);

	CategoriaMovimentacao removeCategoriaMovimentacao(String googleId, Long idCategoria);

	List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorConta(String googleId);

	List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao, String googleId);

	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoria(String googleId, Long dataInicio,
		Long dataFim, String tipoMovimentacao);
	
	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoriaEMeses(String googleId, Long dataInicio,
		Long dataFim, String tipoMovimentacao);
}
