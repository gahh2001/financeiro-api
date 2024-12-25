package org.financeiro.business;

import java.util.List;

import org.financeiro.dto.PlanejamentoDTO;
import org.financeiro.entity.Planejamento;
import org.financeiro.repository.IPlanejamentoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlanejamentoBusiness implements IPlanejamentoBusiness {

	@Inject
	IPlanejamentoRepository repository;

	@Override
	public Planejamento criar(PlanejamentoDTO planejamento) {
		return this.repository.criar(planejamento.entidade());
	}

	@Override
	public Planejamento atualizar(PlanejamentoDTO planejamento) {
		return this.repository.atualizar(planejamento.entidade());
	}

	@Override
	public List<PlanejamentoDTO> listarPorConta(String googleId) {
		return this.repository.listarPorConta(googleId)
			.stream()
			.map(entidade -> entidade.dto())
			.toList();
	}

	@Override
	public void apagar(Long idPlanejamento) {
		this.repository.apagar(idPlanejamento);
	}
}
