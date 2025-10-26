package org.financeiro.business;

import java.util.Date;
import java.util.List;

import org.financeiro.dto.SomaCategoriasPorPeriodoDTO;
import org.financeiro.entity.CategoriaMovimentacao;
import org.financeiro.entity.Conta;
import org.financeiro.entity.Movimentacao;
import org.financeiro.exceptions.NonExistentAccount;
import org.financeiro.repository.CategoriaMovimentacaoRepository;
import org.financeiro.repository.MovimentacaoRepository;
import org.financeiro.repository.SomaCategoriasPorPeriodoRepository;
import org.financeiro.security.TokenSecurity;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoriaMovimentacaoBusiness {

	@Inject
	ContaBusiness contaBusiness;

	@Inject
	CategoriaMovimentacaoRepository repository;

	@Inject
	MovimentacaoRepository repositoryMovimentacao;

	@Inject
	SomaCategoriasPorPeriodoRepository somaCategoriasPorMesRepository;

	@Inject
	TokenSecurity tokenBusiness;

	private static final Logger log = Logger.getLogger(CategoriaMovimentacaoBusiness.class);

	public CategoriaMovimentacao criaCategoriaMovimentacao(CategoriaMovimentacao categoria,
			String token) throws NonExistentAccount {
		String googleId = tokenBusiness.getToken(token);
		categoria.setGoogleId(googleId);
		Conta existente = this.contaBusiness.getAccountByGoogleId(token);
		if (existente == null) {
			throw new NonExistentAccount(categoria.getGoogleId());
		}
		return repository.criaCategoriaMovimentacao(categoria);
	}

	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorConta(String token) {
		String googleId = tokenBusiness.getToken(token);
		return this.repository.listaCategoriasMovimentacaoPorConta(googleId);
	}

	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria, String token) {
		String googleId = tokenBusiness.getToken(token);
		return repository.listaCategoriaMovimentacaoPorId(idCategoria, googleId);
	}

	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(
			String tipoMovimentacao, String token) {
		String googleId = tokenBusiness.getToken(token);
		return repository.listaCategoriasMovimentacaoPorTipoMovimentacao(tipoMovimentacao, googleId);
	}

	public CategoriaMovimentacao removeCategoriaMovimentacao(String token, Long idCategoria) {
		String googleId = tokenBusiness.getToken(token);
		List<Movimentacao> movimentacoesExistentes = repositoryMovimentacao
			.listaMovimentacaoPorIdCategoria(googleId, idCategoria);
		CategoriaMovimentacao paraApagar = listaCategoriaMovimentacaoPorId(idCategoria, token);
		if (movimentacoesExistentes == null || movimentacoesExistentes.isEmpty()) {
			repository.removeCategoriaMovimentacao(idCategoria);
			return paraApagar;
		}
		return null;
	}

	public CategoriaMovimentacao atualizaCategoriaMovimentacao(CategoriaMovimentacao novaCategoria) {
		return repository.atualizaCategoriaMovimentacao(novaCategoria);
	}

	public List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoria(String token, Long dataInicio,
			Long dataFim, String tipoMovimentacao) {
		String googleId = tokenBusiness.getToken(token);
		return this.somaCategoriasPorMesRepository
			.listaSomaPorCategoria(googleId, new Date(dataInicio), new Date(dataFim), tipoMovimentacao);
	}

	public List<SomaCategoriasPorPeriodoDTO> listaSomaPorCategoriaEMeses(String token, Long dataInicio,
			Long dataFim, List<String> categorias) {
		String googleId = tokenBusiness.getToken(token);
		List<Long> idCategorias;
		if (categorias.contains("POSITIVOS")) {
			idCategorias = List.of(-1L);
		} else if (categorias.contains("NEGATIVOS")) {
			idCategorias = List.of(-2L);
		} else {
			idCategorias = this.obtemCategoriasPorNome(googleId, categorias);
		}
		return this.somaCategoriasPorMesRepository
			.listaSomaPorCategoriaEMeses(googleId, new Date(dataInicio),
				new Date(dataFim), idCategorias);
	}

	private List< Long > obtemCategoriasPorNome(String googleId, List<String> categorias) {
		return this.repository.listaIdCategoriasPorNome(googleId, categorias);
	}

	public List<SomaCategoriasPorPeriodoDTO> listaSomaPorTipoEMeses(String token, Long dataInicio,
			Long dataFim) {
		String googleId = tokenBusiness.getToken(token);
		List<SomaCategoriasPorPeriodoDTO> result = this.somaCategoriasPorMesRepository
			.listaSomaPorTipoEMeses(googleId, new Date(dataInicio), new Date(dataFim));
		result.forEach(soma -> 
			soma.setNomeCategoria("POSITIVO".equals(soma.getNomeCategoria()) ? "Rendimentos" : "Despesas"));
		return result;
	}

	public void criaCategoriasIniciais(String token) {
		String googleId = tokenBusiness.getToken(token);
		CategoriaMovimentacao primeira = new CategoriaMovimentacao(
			"POSITIVO", "Depósito", googleId, "#239d12", "DINHEIRO");
		CategoriaMovimentacao segunda = new CategoriaMovimentacao(
			"POSITIVO", "Salário", googleId, "#FFD141", "CARTEIRA");
		CategoriaMovimentacao terceira = new CategoriaMovimentacao(
			"NEGATIVO", "Despesa", googleId, "#e15734db", "CARTAO");
		CategoriaMovimentacao quarta = new CategoriaMovimentacao(
			"NEGATIVO", "Gastos", googleId, "#f45dfa", "FAVORITO");
		try {
			this.criaCategoriaMovimentacao(primeira, token);
			this.criaCategoriaMovimentacao(segunda, token);
			this.criaCategoriaMovimentacao(terceira, token);
			this.criaCategoriaMovimentacao(quarta, token);
		} catch (NonExistentAccount e) {
			log.error("Erro ao criar categorias padrão para a conta " + googleId);
		}
	}
}
