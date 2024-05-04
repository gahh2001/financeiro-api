package org.financeiro.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.financeiro.entity.Conta;
import org.financeiro.repository.IContaRepository;


@ApplicationScoped
public class ContaBusiness implements IContaBusiness {

	@Inject
	IContaRepository contaRepository;

	@Override
	public void processAccount(Conta conta) {
		Conta contaExistente = this.contaRepository.getAccountByGoogleId(conta.getGoogleId());
		if (contaExistente == null) {
			conta.setSaldoConta(0.0);
			conta.setSaldoInvestimento(0.0);
			this.contaRepository.criaconta(conta);
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
	public void removeConta(Long idConta) {
		this.contaRepository.removeConta(idConta);
	}

	@Override
	public void atualizaSaldoConta(Double valorMovimentacao, Long idConta) {
		this.contaRepository.atualizaSaldoConta(valorMovimentacao, idConta);
	}

	@Override
	public void atualizaInvestimento(double novoInvestimento, Long idConta) {
		this.contaRepository.atualizaInvestimento(novoInvestimento, idConta);
	}
}
