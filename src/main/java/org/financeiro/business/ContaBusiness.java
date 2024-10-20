package org.financeiro.business;

import org.financeiro.entity.Conta;
import org.financeiro.repository.IContaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ContaBusiness implements IContaBusiness {

	@Inject
	IContaRepository contaRepository;

	@Inject
	ICategoriaMovimentacaoBusiness categoriasBusiness;

	@Override
	public void processAccount(Conta conta) {
		Conta contaExistente = this.contaRepository.getAccountByGoogleId(conta.getGoogleId());
		if (contaExistente == null) {
			conta.setSaldoConta(0.0);
			conta.setSaldoInvestimento(0.0);
			this.contaRepository.criaconta(conta);
			this.categoriasBusiness.criaCategoriasIniciais(conta.getGoogleId());
		}
		this.contaRepository.updateAccount(conta);
	}

	@Override
	public Conta criaconta(Conta conta) {
		return this.contaRepository
		.criaconta(conta);
	}

	@Override
	public Conta updateAccount(Conta conta) {
		return this.contaRepository.updateAccount(conta);
	}

	@Override
	public Conta listaContaPorId(Long idConta) {
		return this.contaRepository.listaContaPorId(idConta);
	}

	@Override
	public Conta getAccountByGoogleId(String id) {
		return this.contaRepository.getAccountByGoogleId(id);
	}

	@Override
	public void atualizaSaldoConta(Double valorMovimentacao, String googleId) {
		this.contaRepository.atualizaSaldoConta(valorMovimentacao, googleId);
	}

	@Override
	public void atualizaInvestimento(double novoInvestimento, String googleId) {
		this.contaRepository.atualizaInvestimento(novoInvestimento, googleId);
	}

	@Override
	public void zeraSaldo( Conta conta ) {
		this.contaRepository.atualizaSaldoConta( 0D, conta.getGoogleId());
	}
}
