package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.dto.DesempenhoPlanejamentoDTO;
import org.financeiro.entity.Planejamento;

public interface IPlanejamentoRepository {

	Planejamento criar(Planejamento planejamento);
	Planejamento atualizar(Planejamento planejamento);
	List<Planejamento> listarPorConta(String googleId);
	void apagar(Long idPlanejamento);
	Planejamento obtemPorId(Long id);
	Double obtemProgressoPorCategoriasEPeriodo(Date inicio, Date fim, List<Integer> idCategorias, String gooogleId);
	List<DesempenhoPlanejamentoDTO> obtemDesempenho(Planejamento planejamento, Date date);
}
