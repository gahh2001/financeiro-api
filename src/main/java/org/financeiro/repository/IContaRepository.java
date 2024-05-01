package org.financeiro.repository;

import org.financeiro.entity.Conta;

public interface IContaRepository {

	Conta criaconta(Conta conta);
	Conta updateAccount(Conta conta);
	Conta listaContaPorId(Long idConta);
	Conta getAccountByGoogleId(String id);
	void removeConta(Long idConta);
	void atualizaSaldoConta(Double valorMovimentacao, Long idConta);
	void atualizaInvestimento(double novoInvestimento, Long idConta);
}
