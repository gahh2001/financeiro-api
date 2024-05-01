package org.financeiro.business;

import org.financeiro.entity.Conta;
import org.financeiro.repository.IContaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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
}
