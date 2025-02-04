package org.financeiro.security;

import java.io.IOException;

import org.financeiro.enumeration.JWTChave;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.quarkus.security.Authenticated;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Authenticated
public class JwtAuthFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		DecodedJWT decodificador;
		String auth = requestContext.getHeaderString("Authentication");
		try {
			if (auth != null && auth.startsWith("Bearer ")) {
				Algorithm algoritmo = Algorithm.HMAC512(JWTChave.CHAVE.getValue());
				JWTVerifier verificador = JWT.require(algoritmo).build();
				decodificador = verificador.verify(auth.substring(7));
				return;
			}
			throw new IOException();
		} catch (Exception e) {
			requestContext.abortWith(Response
				.status(Response.Status.FORBIDDEN)
				.entity("Acesso negado!")
				.build());
		}
	}
}
