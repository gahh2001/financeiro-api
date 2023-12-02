package org.financeiro.business;

import java.util.List;

import org.financeiro.dto.CategoriaMovimentacaoDTO;
import org.financeiro.entity.CategoriaMovimentacao;

public interface ICategoriaMovimentacaoBusiness {

	CategoriaMovimentacao criaCategoriaMovimentacao(CategoriaMovimentacao categoria);

	CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria);

	CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(String novoNome, Long idCategoria);

	CategoriaMovimentacao removeCategoriaMovimentacao(Long idConta, Long idCategoria);

	List<CategoriaMovimentacaoDTO> listaCategoriasMovimentacao(Long idConta);

	List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao, Long idconta);
}
