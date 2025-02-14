package org.financeiro.security;

import java.io.IOException;

import org.financeiro.enumeration.JWTChave;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)// não sei se precisa mesmo
public class JwtAuthFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println(requestContext.getUriInfo().getPath());
		System.out.println(requestContext.getHeaderString("Authorization"));
		String path = requestContext.getUriInfo().getPath();
		if (path.equals("/login")) {
			return;
		}
		String auth = requestContext.getHeaderString("Authorization");
		try {
			if (auth != null && auth.startsWith("Bearer ")) {
				Algorithm algoritmo = Algorithm.HMAC512(JWTChave.CHAVE.getValue());
				JWTVerifier verificador = JWT.require(algoritmo).build();
				verificador.verify(auth.substring(7));
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
