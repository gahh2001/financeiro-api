package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.entity.SomaCategoriasPorMesDTO;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SomaCategoriasPorMesRepository
		implements PanacheRepository<SomaCategoriasPorMesDTO>, ISomaCategoriasPorMesRepository {

	@Override
	@Transactional
	public List<SomaCategoriasPorMesDTO> listaSomaPorCategoria(Long idConta, Date dataInicio,
			Date dataFim, String tipoMovimentacao) {
		return list("select new org.financeiro.entity.SomaCategoriasPorMesDTO("
				+ "categoria.nomeCategoria, sum(movimentacao.valor) as somaMovimentacao) "
				+ "from Movimentacao movimentacao "
				+ "join CategoriaMovimentacao categoria on movimentacao.idCategoriaMovimentacao = categoria.id "
				+ "where movimentacao.idConta = ?1 "
				+ "and movimentacao.dataMovimentacao between ?2 and ?3 "
				+ "and movimentacao.tipoMovimentacao = ?4 "
				+ "group by categoria.nomeCategoria",
			idConta, dataInicio, dataFim, tipoMovimentacao);
	}
}
