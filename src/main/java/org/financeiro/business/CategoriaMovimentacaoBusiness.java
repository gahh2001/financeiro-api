package org.financeiro.business;

import java.util.Date;
import java.util.List;

import org.financeiro.dto.SomaCategoriasPorPeriodoDTO;
import org.financeiro.entity.CategoriaMovimentacao;
import org.financeiro.entity.Conta;
import org.financeiro.entity.Movimentacao;
import org.financeiro.exceptions.NonExistentAccount;
import org.financeiro.repository.ICategoriaMovimentacaoRepository;
import org.financeiro.repository.IMovimentacaoRepository;
import org.financeiro.repository.ISomaCategoriasPorPeriodoRepository;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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

	private static final Logger log = Logger.getLogger(CategoriaMovimentacaoBusiness.class);

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
	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria, String googleId) {
		return repository.listaCategoriaMovimentacaoPorId(idCategoria, googleId);
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
		CategoriaMovimentacao paraApagar = listaCategoriaMovimentacaoPorId(idCategoria, googleId);
		if (movimentacoesExistentes == null || movimentacoesExistentes.isEmpty()) {
			repository.removeCategoriaMovimentacao(idCategoria);
			return paraApagar;
		}
		return null;
	}

	@Override
	public CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(CategoriaMovimentacao novaCategoria) {
		return repository.atualizaNomeCategoriaMovimentacao(novaCategoria);
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

	@Override
	public List<SomaCategoriasPorPeriodoDTO> listaSomaPorTipoEMeses(String googleId, Long dataInicio,
			Long dataFim) {
		List<SomaCategoriasPorPeriodoDTO> result = this.somaCategoriasPorMesRepository
			.listaSomaPorTipoEMeses(googleId, new Date(dataInicio), new Date(dataFim));
		result.forEach( soma -> 
			soma.setNomeCategoria("POSITIVO".equals(soma.getNomeCategoria()) ? "Rendimentos" : "Despesas"));
		return result;
	}

	@Override
	public void criaCategoriasIniciais(String googleId) {
		CategoriaMovimentacao primeira = new CategoriaMovimentacao(
			"POSITIVO", "Depósito", googleId, "#239d12", "DINHEIRO");
		CategoriaMovimentacao segunda = new CategoriaMovimentacao(
			"POSITIVO", "Salário", googleId, "#FFD141", "CARTEIRA");
		CategoriaMovimentacao terceira = new CategoriaMovimentacao(
			"NEGATIVO", "Despesa", googleId, "#e15734db", "CARTAO");
		CategoriaMovimentacao quarta = new CategoriaMovimentacao(
			"NEGATIVO", "Gastos", googleId, "#f45dfa", "FAVORITO");
		try {
			this.criaCategoriaMovimentacao(primeira);
			this.criaCategoriaMovimentacao(segunda);
			this.criaCategoriaMovimentacao(terceira);
			this.criaCategoriaMovimentacao(quarta);
		} catch (NonExistentAccount e) {
			log.error("Erro ao criar categorias padrão para a conta " + googleId);
		}
	}
}
