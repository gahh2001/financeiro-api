package org.financeiro.repository;

import java.util.List;

import org.financeiro.entity.CategoriaMovimentacao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;



@ApplicationScoped
public class CategoriaMovimentacaoRepository
		implements PanacheRepository<CategoriaMovimentacao>, ICategoriaMovimentacaoRepository {

	@Override
	@Transactional
	public CategoriaMovimentacao criaCategoriaMovimentacao(CategoriaMovimentacao categoria) {
		persist(categoria);
		return categoria;
	}

	@Override
	@Transactional
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorConta(String googleId) {
		return list("select t from CategoriaMovimentacao t where t.googleId = ?1 order by t.nomeCategoria asc", googleId);
	}

	@Override
	@Transactional
	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria, String googleId) {
		List<CategoriaMovimentacao> categoria = list ("select c from CategoriaMovimentacao c "
			+ "where c.id = ?1 and c.googleId = ?2 order by c.nomeCategoria asc", idCategoria, googleId);
		return categoria != null && !categoria.isEmpty() ? categoria.get(0) : null;
	}

	@Override
	@Transactional
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao,
			String googleId) {
		return "TODOS".equals(tipoMovimentacao)
			? this.listaCategoriasMovimentacaoPorConta(googleId)
			: list("select t from CategoriaMovimentacao t where t.tipoMovimentacao = ?1 "
				+ "and t.googleId = ?2 order by t.nomeCategoria asc",
				tipoMovimentacao, googleId);
	}

	@Override
	@Transactional
	public void removeCategoriaMovimentacao(Long idCategoria) {
		deleteById(idCategoria);
	}

	@Override
	@Transactional
	public CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(CategoriaMovimentacao novaCategoria) {
		CategoriaMovimentacao antiga = findById(novaCategoria.getId());
		antiga.setNomeCategoria(novaCategoria.getNomeCategoria());
		antiga.setIcone(novaCategoria.getIcone());
		antiga.setCorIcone(novaCategoria.getCorIcone());
		antiga.persistAndFlush();
		return antiga;
	}
}
