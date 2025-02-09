package org.financeiro.business;

import java.util.List;

import org.financeiro.dto.SomaCategoriasPorPeriodoDTO;
import org.financeiro.entity.CategoriaMovimentacao;
import org.financeiro.exceptions.NonExistentAccount;

public interface ICategoriaMovimentacaoBusiness {

	CategoriaMovimentacao criaCategoriaMovimentacao(CategoriaMovimentacao categoria) throws NonExistentAccount;

	CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria, String token);

	CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(CategoriaMovimentacao novaCategoria);

	CategoriaMovimentacao removeCategoriaMovimentacao(String token, Long idCategoria);

	List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorConta(String token);

	List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao, String token);

	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoria(String token, Long dataInicio,
		Long dataFim, String tipoMovimentacao);

	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoriaEMeses(String token, Long dataInicio,
		Long dataFim, String tipoMovimentacao);

	List<SomaCategoriasPorPeriodoDTO> listaSomaPorTipoEMeses(String token, Long dataInicio,
		Long dataFim);

	void criaCategoriasIniciais(String googleId);
}
