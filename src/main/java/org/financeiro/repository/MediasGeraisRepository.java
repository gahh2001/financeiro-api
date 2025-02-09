package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.dto.MediasGeraisDTO;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MediasGeraisRepository implements PanacheRepository<MediasGeraisDTO>, IMediasGeraisRepository{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public MediasGeraisDTO obtemCategoriaMaisRegistradaPorTipo(String googleId, Date dataInicio,
			Date dataFim, String tipo) {
		List<MediasGeraisDTO> result = list(
			"select new org.financeiro.dto.MediasGeraisDTO(cm.nomeCategoria as categoriaMaisGasta, "
			+ "SUM(m.valor) AS somaMovimentacao) FROM Movimentacao m JOIN CategoriaMovimentacao cm "
			+ "ON m.idCategoriaMovimentacao = cm.id WHERE m.googleId = ?1 and m.dataMovimentacao BETWEEN "
			+ "?2 AND ?3 and m.tipoMovimentacao = ?4 GROUP BY cm.nomeCategoria ORDER BY somaMovimentacao "
			+ "DESC LIMIT 1", googleId, dataInicio, dataFim, tipo);
		return result != null && !result.isEmpty() ? result.get(0) : null;
	}

	@Override
	@Transactional
	public Double obtemMediaMensalPorTipo(String googleId, Date dataInicio, Date dataFim, String tipo) {
		String sql = "WITH MovimentacaoMensal AS (SELECT DATE_TRUNC('month', m.dataMovimentacao) AS mes,"
			+ "SUM(m.valor) AS somaMensal FROM Movimentacao m WHERE m.dataMovimentacao BETWEEN :dataInicio "
			+ "AND :dataFim AND m.tipoMovimentacao = :tipo and m.googleid = :googleid GROUP BY "
			+ "DATE_TRUNC('month', m.dataMovimentacao)) SELECT AVG(somaMensal) AS mediaMensal FROM MovimentacaoMensal";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		query.setParameter("tipo", tipo);
		query.setParameter("googleid", googleId);

		Object result = query.getSingleResult();
		return result != null ? ((Number) result).doubleValue() : 0L;
	}
}
