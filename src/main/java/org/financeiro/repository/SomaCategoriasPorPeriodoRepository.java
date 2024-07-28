package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.dto.SomaCategoriasPorPeriodoDTO;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SomaCategoriasPorPeriodoRepository
		implements PanacheRepository<SomaCategoriasPorPeriodoDTO>, ISomaCategoriasPorPeriodoRepository {

	@Override
	@Transactional
	public List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoria(String googleId, Date dataInicio,
			Date dataFim, String tipoMovimentacao) {
		return list("select new org.financeiro.dto.SomaCategoriasPorPeriodoDTO("
			+ "categoria.nomeCategoria, sum(movimentacao.valor) as somaMovimentacao) "
			+ "from Movimentacao movimentacao "
			+ "join CategoriaMovimentacao categoria on movimentacao.idCategoriaMovimentacao = categoria.id "
			+ "where movimentacao.googleId = ?1 "
			+ "and movimentacao.dataMovimentacao between ?2 and ?3 "
			+ "and movimentacao.tipoMovimentacao = ?4 "
			+ "group by categoria.nomeCategoria "
			+ "order by somaMovimentacao desc",
			googleId, dataInicio, dataFim, tipoMovimentacao);
	}

	@Override
	@Transactional
	public List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoriaEMeses(String googleId, Date dataInicio,
			Date dataFim, String tipoMovimentacao) {
		return list("SELECT new org.financeiro.dto.SomaCategoriasPorPeriodoDTO(cm.nomeCategoria, "
			+ "m.dataMovimentacao as data, SUM(m.valor) AS somaMovimentacao) FROM Movimentacao m JOIN "
			+ "CategoriaMovimentacao cm ON m.idCategoriaMovimentacao = cm.id WHERE m.googleId = "
			+ "?1 AND m.dataMovimentacao BETWEEN ?2 AND ?3 AND m.tipoMovimentacao = ?4 GROUP BY "
			+ "cm.nomeCategoria, m.dataMovimentacao ORDER BY m.dataMovimentacao ASC",
			googleId, dataInicio, dataFim, tipoMovimentacao);
	}

	@Override
	@Transactional
	public List<SomaCategoriasPorPeriodoDTO> listaSomaPorTipoEMeses(String googleId, Date dataInicio,
			Date dataFim) {
		return list("select new org.financeiro.dto.SomaCategoriasPorPeriodoDTO(cm.tipoMovimentacao "
		+ "AS nomeCategoria, DATE_TRUNC('month', m.dataMovimentacao) as data, SUM(m.valor) AS somaMovimentacao) FROM "
		+ "Movimentacao m JOIN CategoriaMovimentacao cm ON m.idCategoriaMovimentacao = cm.id WHERE "
		+ "m.googleId = ?1 AND m.dataMovimentacao BETWEEN ?2 AND ?3 GROUP BY cm.tipoMovimentacao, "
		+ "DATE_TRUNC('month', m.dataMovimentacao) ORDER BY data ASC, cm.tipoMovimentacao", googleId, dataInicio, dataFim);
	}
}
