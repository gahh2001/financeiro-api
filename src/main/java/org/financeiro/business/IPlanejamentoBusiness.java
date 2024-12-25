package org.financeiro.business;

import java.util.List;

import org.financeiro.dto.PlanejamentoDTO;
import org.financeiro.entity.Planejamento;

public interface IPlanejamentoBusiness {

	Planejamento criar(PlanejamentoDTO planejamento);
	Planejamento atualizar(PlanejamentoDTO planejamento);
	List< PlanejamentoDTO > listarPorConta(String googleId);
	void apagar(Long idPlanejamento);
}
