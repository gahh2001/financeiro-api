package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.dto.SomaCategoriasPorPeriodoDTO;

public interface ISomaCategoriasPorPeriodoRepository {

	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoria(String googleId, Date dataInicio,
		Date dataFim, String tipoMovimentacao);
	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoriaEMeses(String googleId, Date dataInicio,
		Date dataFim, List<Long> categorias);
	List<SomaCategoriasPorPeriodoDTO> listaSomaPorTipoEMeses(String googleId, Date dataInicio,
		Date dataFim);
}
