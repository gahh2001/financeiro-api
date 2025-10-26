package org.financeiro.business;

import org.financeiro.entity.Conta;
import org.financeiro.repository.ContaRepository;
import org.financeiro.security.TokenSecurity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ContaBusiness {

	@Inject
	ContaRepository contaRepository;

	@Inject
	CategoriaMovimentacaoBusiness categoriasBusiness;

	@Inject
	TokenSecurity tokenBusiness;

	public void processAccount(Conta conta, String token) {
		Conta contaExistente = this.contaRepository.getAccountByGoogleId(conta.getGoogleId());
		if (contaExistente == null) {
			conta.setSaldoConta(0.0);
			this.contaRepository.criaconta(conta);
			this.categoriasBusiness.criaCategoriasIniciais(token);
		}
		this.contaRepository.updateAccount(conta);
	}

	public Conta criaconta(Conta conta) {
		return this.contaRepository
		.criaconta(conta);
	}

	public Conta updateAccount(Conta conta) {
		return this.contaRepository.updateAccount(conta);
	}

	public Conta listaContaPorId(Long idConta) {
		return this.contaRepository.listaContaPorId(idConta);
	}

	public Conta getAccountByGoogleId(String token) {
		String googleId = tokenBusiness.getToken(token);
		return this.contaRepository.getAccountByGoogleId(googleId);
	}

	public void atualizaSaldoConta(Double valorMovimentacao, String googleId) {
		this.contaRepository.atualizaSaldoConta(valorMovimentacao, googleId);
	}

	public void atualizaInvestimento(double novoInvestimento, String googleId) {
		this.contaRepository.atualizaInvestimento(novoInvestimento, googleId);
	}

	public void editarSaldo(Conta conta, Double valor) {
		this.contaRepository.atualizaSaldoConta(valor, conta.getGoogleId());
	}
}
