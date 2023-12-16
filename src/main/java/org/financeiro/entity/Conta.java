package org.financeiro.entity;

import jakarta.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Conta extends PanacheEntity {

	private Double saldoConta;
	private Double saldoInvestimento;
	private Long idUsuario;

	public Conta() {
	}

	public Conta(Double saldo, Double investimento, Long idUsuario) {
		this.saldoConta = saldo;
		this.saldoInvestimento = investimento;
		this.idUsuario = idUsuario;
	}

	public Double getSaldoConta() {
		return saldoConta;
	}

	public void setSaldoConta(Double saldoDisponível) {
		this.saldoConta = saldoDisponível;
	}

	public Double getSaldoInvestimento() {
		return saldoInvestimento;
	}

	public void setSaldoInvestimento(Double investimento) {
		this.saldoInvestimento = investimento;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

}