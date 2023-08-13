package org.financeiro.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.financeiro.entity.CategoriaMovimentacao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CategoriaMovimentacaoRepository implements PanacheRepository<CategoriaMovimentacao>, ICategoriaMovimentacaoRepository {

	@Override
	@Transactional
	public CategoriaMovimentacao criaCategoriaMovimentacao( CategoriaMovimentacao categoria) {
		persist(categoria);
		return categoria;
	}

	@Override
	@Transactional
	public List<CategoriaMovimentacao> listaCategoriasMovimentacao(Long idConta) {
		return list("select t from CategoriaMovimentacao t where t.idConta = ?1", idConta);
	}

	@Override
	@Transactional
	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria) {
		return findById(idCategoria);
	}

	@Override
	@Transactional
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao, Long idconta) {
		return list("select t from CategoriaMovimentacao t where t.tipoMovimentacao = ?1 and t.idConta = ?2", tipoMovimentacao, idconta);
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
