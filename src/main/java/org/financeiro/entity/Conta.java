package org.financeiro.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Conta extends PanacheEntity {

	private Double saldoConta;
	private String googleId;
	private String primeiroNome;
	private String sobrenome;
	private String nome;
	private String email;
	private String foto;

	public Conta() {
	}

	public Conta(Double saldo, Long idUsuario) {
		this.saldoConta = saldo;
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