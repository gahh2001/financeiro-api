package org.financeiro.repository;

import java.util.List;

import org.financeiro.entity.Planejamento;

public interface IPlanejamentoRepository {

	Planejamento criar(Planejamento planejamento);
	Planejamento atualizar(Planejamento planejamento);
	List<Planejamento> listarPorConta(String googleId);
	void apagar(Long idPlanejamento);
}
