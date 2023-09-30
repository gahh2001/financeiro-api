package org.financeiro.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.financeiro.entity.Conta;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ContaRepository implements IContaRepository, PanacheRepository<Conta>{
	
	@Override
	@Transactional
	public Conta criaconta(Conta conta) {
		persist(conta);
		return conta;
	}

	@Override
	@Transactional
	public Conta listaContaPorId(Long idConta) {
		return this.findById(idConta);
	}

	@Override
	@Transactional
	public List<Conta> listaContaPorIdUsuario(Long idUsuario) {
		return list("select t from Conta t where t.idUsuario = ?1", idUsuario);
	}

	@Override
	@Transactional
	public void removeConta(Long idConta) {
		deleteById(idConta);
	}

	@Override
	@Transactional
	public void atualizaSaldoConta(Double novoSaldo, Long idConta) {
		update("update Conta t set t.saldoConta = ?1 where t.id = ?2 ", novoSaldo, idConta);
	}

	@Override
	@Transactional
	public void atualizaInvestimento(double novoInvestimento, Long idConta) {
		update("update Conta t set t.saldoInvestimento = ?1 where t.id = ?2", novoInvestimento, idConta);
	}
}
