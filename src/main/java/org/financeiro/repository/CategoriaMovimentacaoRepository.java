package org.financeiro.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import org.financeiro.entity.CategoriaMovimentacao;



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
		return list("select t from CategoriaMovimentacao t where t.googleId = ?1", googleId);
	}

	@Override
	@Transactional
	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria, String googleId) {
		List<CategoriaMovimentacao> categoria = list ("select c from CategoriaMovimentacao c "
			+ "where c.id = ?1 and c.googleId = ?2", idCategoria, googleId);
		return categoria != null && !categoria.isEmpty() ? categoria.get(0) : null;
	}

	@Override
	@Transactional
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao,
			String googleId) {
		return list("select t from CategoriaMovimentacao t where t.tipoMovimentacao = ?1 and t.googleId = ?2",
			tipoMovimentacao, googleId);
	}

	@Override
	@Transactional
	public void removeCategoriaMovimentacao(Long idCategoria) {
		deleteById(idCategoria);
	}

	@Override
	@Transactional
	public CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(String novoNome, Long idCategoria) {
		update("update CategoriaMovimentacao t set t.nomeCategoria = ?1 where t.id = ?2", novoNome, idCategoria);
		return findById(idCategoria);
	}
}
