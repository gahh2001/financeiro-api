package org.financeiro.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Date;
import java.util.List;
import org.financeiro.entity.CategoriaMovimentacao;
import org.financeiro.entity.Conta;
import org.financeiro.entity.Movimentacao;
import org.financeiro.entity.SomaCategoriasPorPeriodoDTO;
import org.financeiro.exceptions.NonExistentAccount;
import org.financeiro.repository.ICategoriaMovimentacaoRepository;
import org.financeiro.repository.IMovimentacaoRepository;
import org.financeiro.repository.ISomaCategoriasPorPeriodoRepository;

@ApplicationScoped
public class CategoriaMovimentacaoBusiness implements ICategoriaMovimentacaoBusiness {

	@Inject
	IContaBusiness contaBusiness;
	@Inject
	ICategoriaMovimentacaoRepository repository;
	@Inject
	IMovimentacaoRepository repositoryMovimentacao;
	@Inject
	ISomaCategoriasPorPeriodoRepository somaCategoriasPorMesRepository;

	@Override
	public CategoriaMovimentacao criaCategoriaMovimentacao(CategoriaMovimentacao categoria) throws NonExistentAccount {
		Conta existente = this.contaBusiness.getAccountByGoogleId(categoria.getGoogleId());
		if (existente == null) {
			throw new NonExistentAccount(categoria.getGoogleId());
		}
		return repository.criaCategoriaMovimentacao(categoria);
	}

	@Override
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorConta(String googleId) {
		return this.repository.listaCategoriasMovimentacaoPorConta(googleId);
	}

	@Override
	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria) {
		return repository.listaCategoriaMovimentacaoPorId(idCategoria);
	}

	@Override
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao,
			String googleId) {
		return repository.listaCategoriasMovimentacaoPorTipoMovimentacao(tipoMovimentacao, googleId);
	}

	@Override
	public CategoriaMovimentacao removeCategoriaMovimentacao(String googleId, Long idCategoria) {
		List<Movimentacao> movimentacoesExistentes = repositoryMovimentacao.listaMovimentacaoPorIdCategoria(googleId,
				idCategoria);
		CategoriaMovimentacao paraApagar = listaCategoriaMovimentacaoPorId(idCategoria);
		if (movimentacoesExistentes == null | movimentacoesExistentes.isEmpty()) {
			repository.removeCategoriaMovimentacao(idCategoria);
			return paraApagar;
		}
		return null;
	}

	@Override
	public CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(String novoNome, Long idCategoria) {
		return repository.atualizaNomeCategoriaMovimentacao(novoNome, idCategoria);
	}

	@Override
	public List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoria(String googleId, Long dataInicio,
			Long dataFim, String tipoMovimentacao) {
		return this.somaCategoriasPorMesRepository
			.listaSomaPorCategoria(googleId, new Date(dataInicio), new Date(dataFim), tipoMovimentacao);
	}

	@Override
	public List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoriaEMeses(String googleId, Long dataInicio,
			Long dataFim, String tipoMovimentacao) {
		return this.somaCategoriasPorMesRepository
			.listaSomaPorCategoriaEMeses(googleId, new Date(dataInicio),
				new Date(dataFim), tipoMovimentacao);
	}
}
