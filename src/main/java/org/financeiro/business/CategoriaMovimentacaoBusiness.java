package org.financeiro.business;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.financeiro.entity.CategoriaMovimentacao;
import org.financeiro.entity.Movimentacao;
import org.financeiro.repository.ICategoriaMovimentacaoRepository;
import org.financeiro.repository.IMovimentacaoRepository;

@ApplicationScoped
public class CategoriaMovimentacaoBusiness implements ICategoriaMovimentacaoBusiness {
	
	@Inject
	ICategoriaMovimentacaoRepository repository;
	@Inject
	IMovimentacaoRepository repositoryMovimentacao;

	@Override
	public CategoriaMovimentacao criaCategoriaMovimentacao( CategoriaMovimentacao categoria) {
		return repository.criaCategoriaMovimentacao(categoria);
	}

	@Override
	public List<CategoriaMovimentacao> listaCategoriasMovimentacao(Long idConta) {
		return repository.listaCategoriasMovimentacao(idConta);
	}

	@Override
	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria) {
		return repository.listaCategoriaMovimentacaoPorId(idCategoria);
	}

	@Override
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao, Long idconta) {
		return repository.listaCategoriasMovimentacaoPorTipoMovimentacao(tipoMovimentacao, idconta);
	}

	@Override
	public CategoriaMovimentacao removeCategoriaMovimentacao(Long idConta, Long idCategoria) {
		List<Movimentacao> movimentacaosExistentes = repositoryMovimentacao.listaMovimentacaoPorIdCategoria(idConta, idCategoria);
		CategoriaMovimentacao paraApagar = listaCategoriaMovimentacaoPorId(idCategoria);
		if (movimentacaosExistentes == null | movimentacaosExistentes.isEmpty()) {
			repository.removeCategoriaMovimentacao(idCategoria);
			return paraApagar;
		}
		return null;
	}

	@Override
	public CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(String novoNome, Long idCategoria) {
		return repository.atualizaNomeCategoriaMovimentacao(novoNome, idCategoria);
	}
}
