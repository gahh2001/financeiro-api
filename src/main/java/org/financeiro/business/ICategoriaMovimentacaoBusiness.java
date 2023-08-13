package org.financeiro.business;

import java.util.List;

import org.financeiro.entity.CategoriaMovimentacao;

public interface ICategoriaMovimentacaoBusiness {
	
	CategoriaMovimentacao criaCategoriaMovimentacao( CategoriaMovimentacao categoria);
	CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria);
	CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(String novoNome, Long idCategoria);
	CategoriaMovimentacao removeCategoriaMovimentacao(Long idConta, Long idCategoria);
	List<CategoriaMovimentacao> listaCategoriasMovimentacao(Long idConta);
	List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao, Long idconta);
}
