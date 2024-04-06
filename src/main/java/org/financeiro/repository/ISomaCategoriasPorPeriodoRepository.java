package org.financeiro.repository;

import java.util.Date;
import java.util.List;
import org.financeiro.entity.SomaCategoriasPorPeriodoDTO;


public interface ISomaCategoriasPorPeriodoRepository {

	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoria(Long idConta, Date dataInicio,
		Date dataFim, String tipoMovimentacao);
	List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoriaEMeses(Long idConta, Date dataInicio,
		Date dataFim, String tipoMovimentacao);
}
