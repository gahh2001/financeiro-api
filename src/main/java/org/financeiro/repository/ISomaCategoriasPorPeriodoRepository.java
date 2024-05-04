package org.financeiro.repository;

import java.util.Date;
import java.util.List;
import org.financeiro.entity.SomaCategoriasPorPeriodoDTO;


public interface ISomaCategoriasPorPeriodoRepository {

	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoria(String googleId, Date dataInicio,
		Date dataFim, String tipoMovimentacao);
	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoriaEMeses(String googleId, Date dataInicio,
		Date dataFim, String tipoMovimentacao);
}
