package org.financeiro.repository;

import java.util.List;

import org.financeiro.entity.CategoriaMovimentacao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoriaMovimentacaoRepository
		implements PanacheRepository<CategoriaMovimentacao> {

	@Transactional
	public CategoriaMovimentacao criaCategoriaMovimentacao(CategoriaMovimentacao categoria) {
		persist(categoria);
		return categoria;
	}

	@Transactional
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorConta(String googleId) {
		return list("select t from CategoriaMovimentacao t where t.googleId = ?1 order by t.nomeCategoria asc", googleId);
	}

	@Transactional
	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria, String googleId) {
		List<CategoriaMovimentacao> categoria = list ("select c from CategoriaMovimentacao c "
			+ "where c.id = ?1 and c.googleId = ?2 order by c.nomeCategoria asc", idCategoria, googleId);
		return categoria != null && !categoria.isEmpty() ? categoria.get(0) : null;
	}

	@Transactional
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao,
			String googleId) {
		return "TODOS".equals(tipoMovimentacao)
			? this.listaCategoriasMovimentacaoPorConta(googleId)
			: list("select t from CategoriaMovimentacao t where t.tipoMovimentacao = ?1 "
				+ "and t.googleId = ?2 order by t.nomeCategoria asc",
				tipoMovimentacao, googleId);
	}

	@Transactional
	public void removeCategoriaMovimentacao(Long idCategoria) {
		deleteById(idCategoria);
	}

	@Transactional
	public CategoriaMovimentacao atualizaCategoriaMovimentacao(CategoriaMovimentacao novaCategoria) {
		CategoriaMovimentacao antiga = findById(novaCategoria.getId());
		antiga.setNomeCategoria(novaCategoria.getNomeCategoria());
		antiga.setIcone(novaCategoria.getIcone());
		antiga.setCorIcone(novaCategoria.getCorIcone());
		antiga.setValorPadrao(novaCategoria.getValorPadrao());
		antiga.persistAndFlush();
		return antiga;
	}

	@Transactional
	public List<Long> listaIdCategoriasPorNome(String googleId, List<String> nomes) {
		return getEntityManager()
			.createQuery("SELECT c.id FROM CategoriaMovimentacao c WHERE c.googleId = :googleId AND c.nomeCategoria IN :nomes", Long.class)
			.setParameter("googleId", googleId)
			.setParameter("nomes", nomes)
			.getResultList();
	}
}
