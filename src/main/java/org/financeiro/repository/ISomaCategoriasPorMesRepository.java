package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.entity.SomaCategoriasPorMesDTO;

public interface ISomaCategoriasPorMesRepository {

	List<SomaCategoriasPorMesDTO> listaSomaPorCategoria(Long idConta, Date dataInicio,
		Date dataFim, String tipoMovimentacao);
}
