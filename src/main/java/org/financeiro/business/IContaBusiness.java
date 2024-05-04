package org.financeiro.business;

import org.financeiro.entity.Conta;

public interface IContaBusiness {

	void processAccount(Conta conta);
	Conta criaconta(Conta conta);
	Conta updateAccount(Conta conta);
	Conta listaContaPorId(Long idConta);
	Conta getAccountByGoogleId(String id);
	void removeConta(Long idConta);
	void atualizaSaldoConta(Double valorMovimentacao, Long idConta);
	void atualizaInvestimento(double novoInvestimento, Long idConta);
}
