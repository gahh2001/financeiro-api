package org.financeiro.repository;

import java.util.List;

import org.financeiro.entity.CategoriaMovimentacao;

public interface ICategoriaMovimentacaoRepository {

	CategoriaMovimentacao criaCategoriaMovimentacao(CategoriaMovimentacao categoria);

	CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria);

	CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(String novoNome, Long idCategoria);

	void removeCategoriaMovimentacao(Long idCategoria);

	List<CategoriaMovimentacao> listaCategoriasMovimentacao(String googleId);

	List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao, String googleId);
}