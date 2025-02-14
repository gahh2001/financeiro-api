package org.financeiro.security;

import org.financeiro.enumeration.JWTChave;
import org.jboss.logging.Logger;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenSecurity {

	private static final Logger LOG = Logger.getLogger(TokenSecurity.class);

	public String getToken(String token) {
		DecodedJWT decodificador;
		Algorithm algoritmo = Algorithm.HMAC512(JWTChave.CHAVE.getValue());
		JWTVerifier verificador = JWT.require(algoritmo).build();
		decodificador = verificador.verify(token.substring(7));
		LOG.info("googleId: " + decodificador.getClaim("googleId").asString());
		return decodificador.getClaim("googleId").asString();
	}
}
