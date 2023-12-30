package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.entity.SomaCategoriasPorMesDTO;

public interface ISomaCategoriasPorMesRepository {

	List<SomaCategoriasPorMesDTO> listaCategoriasEValoresNoMes(Long idConta, Date dataInicio, Date dataFim);
}
