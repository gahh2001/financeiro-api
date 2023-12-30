package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.entity.SomaCategoriasPorMes;

public interface ISomaCategoriasPorMesRepository {

	List<SomaCategoriasPorMes> listaCategoriasEValoresNoMes(Long idConta, Date dataInicio, Date dataFim);
}
