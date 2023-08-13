package org.financeiro.entity;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Conta extends PanacheEntity{

	private Double saldo;
	private Double investimento;
	private Long idUsuario;

	public Conta() {
	}

	public Conta(Double saldo, Double investimento, Long idUsuario) {
		this.saldo = saldo;
		this.investimento = investimento;
		this.idUsuario = idUsuario;
	}
	
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldoDisponível) {
		this.saldo = saldoDisponível;
	}
	public Double getInvestimento() {
		return investimento;
	}
	public void setInvestimento(Double investimento) {
		this.investimento = investimento;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

}
