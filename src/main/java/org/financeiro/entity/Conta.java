package org.financeiro.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Conta extends PanacheEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double saldoConta;
	private Double saldoInvestimento;
	private String googleId;
	private String primeiroNome;
	private String sobrenome;
	private String nome;
	private String email;
	private String foto;

	public Conta() {
	}

	public Conta(Double saldo, Double investimento, Long idUsuario) {
		this.saldoConta = saldo;
		this.saldoInvestimento = investimento;
	}

	public Long getId() {
		return id;
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

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId( String googleId ) {
		this.googleId = googleId;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome( String firstName ) {
		this.primeiroNome = firstName;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome( String secondName ) {
		this.sobrenome = secondName;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String name ) {
		this.nome = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto( String picture ) {
		this.foto = picture;
	}

}