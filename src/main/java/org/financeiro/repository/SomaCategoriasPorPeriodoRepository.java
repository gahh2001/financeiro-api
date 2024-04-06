package org.financeiro.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import org.financeiro.entity.SomaCategoriasPorPeriodoDTO;







@ApplicationScoped
public class SomaCategoriasPorPeriodoRepository
		implements PanacheRepository<SomaCategoriasPorPeriodoDTO>, ISomaCategoriasPorPeriodoRepository {

	@Override
	@Transactional
	public List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoria(Long idConta, Date dataInicio,
			Date dataFim, String tipoMovimentacao) {
		return list("select new org.financeiro.entity.SomaCategoriasPorPeriodoDTO("
			+ "categoria.nomeCategoria, sum(movimentacao.valor) as somaMovimentacao) "
			+ "from Movimentacao movimentacao "
			+ "join CategoriaMovimentacao categoria on movimentacao.idCategoriaMovimentacao = categoria.id "
			+ "where movimentacao.idConta = ?1 "
			+ "and movimentacao.dataMovimentacao between ?2 and ?3 "
			+ "and movimentacao.tipoMovimentacao = ?4 "
			+ "group by categoria.nomeCategoria",
			idConta, dataInicio, dataFim, tipoMovimentacao);
	}

	@Override
	@Transactional
	public List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoriaEMeses(Long idConta, Date dataInicio,
			Date dataFim, String tipoMovimentacao) {
		return list("SELECT new org.financeiro.entity.SomaCategoriasPorPeriodoDTO ("
			+ "cm.nomeCategoria, EXTRACT(MONTH FROM m.dataMovimentacao) "
			+ "AS mes, EXTRACT(YEAR FROM m.dataMovimentacao) AS ano, SUM(m.valor) AS "
			+ "somaMovimentacao) FROM Movimentacao m JOIN CategoriaMovimentacao cm ON "
			+ "m.idCategoriaMovimentacao = cm.id WHERE m.idConta = ?1 and m.dataMovimentacao "
			+ "BETWEEN ?2 AND ?3 and m.tipoMovimentacao = ?4 GROUP BY cm.nomeCategoria, "
			+ "EXTRACT(MONTH FROM m.dataMovimentacao), EXTRACT(YEAR FROM m.dataMovimentacao)",
			idConta, dataInicio, dataFim, tipoMovimentacao);
	}
}
