package org.financeiro.business;

import org.financeiro.entity.Conta;

public interface IContaBusiness {

	void processAccount(Conta conta, String token);
	Conta criaconta(Conta conta);
	Conta updateAccount(Conta conta);
	Conta listaContaPorId(Long idConta);
	Conta getAccountByGoogleId(String token);
	void atualizaSaldoConta(Double valorMovimentacao, String googleId);
	void atualizaInvestimento(double novoInvestimento, String googleId);
	void editarSaldo(Conta conta, Double valor);
}
