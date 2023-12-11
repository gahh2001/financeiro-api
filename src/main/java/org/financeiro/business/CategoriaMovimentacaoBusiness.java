package org.financeiro.business;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.financeiro.dto.CategoriaMovimentacaoDTO;
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
	public List<CategoriaMovimentacaoDTO> listaCategoriasMovimentacao(Long idConta) {
		List<CategoriaMovimentacao> categorias = repository.listaCategoriasMovimentacao(idConta);
		List<CategoriaMovimentacaoDTO> categoriasDTO = new ArrayList<>();
		categorias.forEach(categoria -> {
			categoriasDTO.add(new CategoriaMovimentacaoDTO(categoria.getId(), categoria.getTipoMovimentacao(),
				categoria.getNomeCategoria(), categoria.getIdConta()));
		});
		return categoriasDTO;
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
