package org.financeiro.repository;

import org.financeiro.entity.Conta;

public interface IContaRepository {

	Conta criaconta(Conta conta);
	Conta updateAccount(Conta conta);
	Conta listaContaPorId(Long idConta);
	Conta getAccountByGoogleId(String id);
	void atualizaSaldoConta(Double valorMovimentacao, String googleId);
	void atualizaInvestimento(double novoInvestimento, String googleId);
}
