package org.financeiro.repository;

import java.util.List;

import org.financeiro.entity.Planejamento;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlanejamentoRepository implements IPlanejamentoRepository, PanacheRepository<Planejamento> {

	@Override
	public Planejamento criar( Planejamento planejamento ) {
		this.persist(planejamento);
		return planejamento;
	}

	@Override
	public Planejamento atualizar( Planejamento planejamento ) {
		this.persistAndFlush(planejamento);
		return planejamento;
	}

	@Override
	public List< Planejamento > listarPorConta( String googleId ) {
		return this.list("select p from Planejamento p where p.googleId = ?1", googleId);
	}

	@Override
	public void apagar( Long idPlanejamento ) {
		this.deleteById(idPlanejamento);
	}
}
