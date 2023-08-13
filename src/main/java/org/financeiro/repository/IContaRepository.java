package org.financeiro.repository;

import java.util.List;

import org.financeiro.entity.Conta;

public interface IContaRepository {

	Conta criaconta(Conta conta);
	Conta listaContaPorId(Long idConta);
	List<Conta> listaContaPorIdUsuario(Long idUsuario);
	void removeConta(Long idConta);
	void atualizaSaldoConta(Double valorMovimentacao, Long idConta);
	void atualizaInvestimento(double novoInvestimento, Long idConta);

}
