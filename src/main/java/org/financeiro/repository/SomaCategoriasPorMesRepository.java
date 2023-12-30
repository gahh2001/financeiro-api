package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.entity.SomaCategoriasPorMes;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SomaCategoriasPorMesRepository
		implements PanacheRepository<SomaCategoriasPorMes>, ISomaCategoriasPorMesRepository {

	@Override
	@Transactional
	public List<SomaCategoriasPorMes> listaCategoriasEValoresNoMes(Long idConta, Date dataInicio, Date dataFim) {
		return list("select categoria.nomeCategoria as nomeCategoria, sum(movimentacao.valor) as somaMovimentacao " +
				"from Movimentacao movimentacao " +
				"join CategoriaMovimentacao categoria on movimentacao.idCategoriaMovimentacao = categoria.id " +
				"where movimentacao.dataMovimentacao between ?1 and ?2 " +
				"group by categoria.nomeCategoria", dataInicio, dataFim);
	}
}
