package org.financeiro.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Conta extends PanacheEntity {

	private Double saldoConta;
	private String googleId;
	private String primeiroNome;
	private String sobrenome;
	private String nome;
	private String email;
	private String foto;


	public Conta(Double saldo, Long idUsuario) {
		this.saldoConta = saldo;
	}
}