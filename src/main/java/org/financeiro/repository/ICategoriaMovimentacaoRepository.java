package org.financeiro.repository;

import java.util.List;

import org.financeiro.entity.CategoriaMovimentacao;

public interface ICategoriaMovimentacaoRepository {

	CategoriaMovimentacao criaCategoriaMovimentacao(CategoriaMovimentacao categoria);

	CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria, String googleId);

	CategoriaMovimentacao atualizaCategoriaMovimentacao(CategoriaMovimentacao novaCategoria);

	void removeCategoriaMovimentacao(Long idCategoria);

	List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorConta(String googleId);

	List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao, String googleId);

	List<Long> listaIdCategoriasPorNome(String googleId, List<String> nomes);
}