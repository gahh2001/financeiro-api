package org.financeiro.repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.financeiro.dto.DesempenhoPlanejamentoDTO;
import org.financeiro.entity.Planejamento;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlanejamentoRepository implements IPlanejamentoRepository, PanacheRepository<Planejamento> {

	@Override
	@Transactional
	public Planejamento criar( Planejamento planejamento ) {
		this.persist(planejamento);
		return planejamento;
	}

	@Override
	@Transactional
	public Planejamento atualizar( Planejamento planejamento ) {
		Planejamento existente = this.findById(planejamento.getId());
		if (existente != null) {
			existente.setAtivo(planejamento.getAtivo());
			existente.setNome(planejamento.getNome());
			existente.setTipo(planejamento.getTipo());
			existente.setRecorrencia(planejamento.getRecorrencia());
			existente.setValor(planejamento.getValor());
			existente.setDataInicio(planejamento.getDataInicio());
			existente.setDataFim(planejamento.getDataFim());
			existente.setCategorias(planejamento.getCategorias());
			this.persistAndFlush(existente);
		}
		return planejamento;
	}

	@Override
	@Transactional
	public List< Planejamento > listarPorConta( String googleId ) {
		return this.list("select p from Planejamento p where p.googleId = ?1 order by p.dataInicio asc", googleId);
	}

	@Override
	@Transactional
	public void apagar( Long idPlanejamento ) {
		this.deleteById(idPlanejamento);
	}

	@Override
	@Transactional
	public Planejamento obtemPorId(Long id) {
		return this.findById(id);
	}

	@Override
	@Transactional
	public Double obtemProgressoPorCategoriasEPeriodo(Date inicio, Date fim, List<Integer> idCategorias, String googleId) {
		String criterioCategoria = idCategorias.contains(-1) ? "and m.tipomovimentacao = 'POSITIVO' "
			: idCategorias.contains(-2) ? "and m.tipomovimentacao = 'NEGATIVO' "
			: "and m.idcategoriamovimentacao in :categorias ";
		String sql = "select SUM(m.valor) from movimentacao m where m.datamovimentacao between :inicio and :fim "
			+ criterioCategoria + "and m.googleid = :googleId";
		Query query = getEntityManager()
			.createNativeQuery(sql)
			.setParameter("inicio", inicio)
			.setParameter("fim", fim)
			.setParameter("googleId", googleId);
		if (criterioCategoria.contains(" in :categorias")) {
			query.setParameter("categorias", idCategorias);
		}
		return (Double) query.getSingleResult();
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DesempenhoPlanejamentoDTO> obtemDesempenho(Planejamento planejamento, Date fim) {
		String criterioCategoria = planejamento.getCategorias().contains("-1")
			? "and m.tipomovimentacao = 'POSITIVO' " : planejamento.getCategorias().contains("-2")
			? "and m.tipomovimentacao = 'NEGATIVO' " : "and m.idcategoriamovimentacao in :categorias ";
		String sql = "select sum(m.valor) as valor, DATE_TRUNC('month', m.dataMovimentacao) as data "
			+ "from Movimentacao m where m.googleId = :googleId and m.dataMovimentacao between :inicio "
			+ "and :fim "  + criterioCategoria + "group by DATE_TRUNC('month', m.dataMovimentacao)";
		Query query = getEntityManager()
			.createNativeQuery(sql)
			.unwrap(org.hibernate.query.NativeQuery.class)
			.setTupleTransformer((tuple, aliases) -> 
				new DesempenhoPlanejamentoDTO(
					((Number) tuple[0]).doubleValue(),
					Date.from(((Instant) tuple[1]))
				)
			)
			.setParameter("inicio", planejamento.getDataInicio())
			.setParameter("fim", fim)
			.setParameter("googleId", planejamento.getGoogleId());
		if (criterioCategoria.contains(" in :categorias")) {
			query.setParameter("categorias", new Gson().fromJson(planejamento.getCategorias(),
				new TypeToken<List<Integer>>() {}.getType()));
		}
		return (List<DesempenhoPlanejamentoDTO>) query.getResultList();
	}
}
