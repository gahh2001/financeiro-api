package org.financeiro.security;

import org.financeiro.enumeration.JWTChave;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenSecurity {

	public String getToken(String token) {
		DecodedJWT decodificador;
		Algorithm algoritmo = Algorithm.HMAC512(JWTChave.CHAVE.getValue());
		JWTVerifier verificador = JWT.require(algoritmo).build();
		decodificador = verificador.verify(token.substring(7));
		System.out.println("googleId: " + decodificador.getClaim("googleId").asString());
		return decodificador.getClaim("googleId").asString();
	}
}
