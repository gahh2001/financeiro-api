package org.financeiro.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import org.financeiro.entity.Conta;

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
	public Conta updateAccount(Conta conta) {
		Conta existente = this.getAccountByGoogleId(conta.getGoogleId());
		existente.setEmail(conta.getEmail());
		existente.setFoto(conta.getFoto());
		existente.setNome(conta.getNome());
		existente.setPrimeiroNome(conta.getPrimeiroNome());
		existente.setSobrenome(conta.getSobrenome());
		this.persistAndFlush(existente);
		return conta;
	}

	@Override
	@Transactional
	public Conta listaContaPorId(Long idConta) {
		return this.findById(idConta);
	}

	@Override
	@Transactional
	public Conta getAccountByGoogleId(String id) {
		List<Conta> conta = list("select t from Conta t where t.googleId = ?1", id);
		return conta == null || conta.isEmpty() ? null : conta.get(0);
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
