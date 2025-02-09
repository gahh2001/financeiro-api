package org.financeiro.business;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.financeiro.dto.LoginDTO;
import org.financeiro.entity.Conta;
import org.financeiro.enumeration.GoogleClientId;
import org.financeiro.enumeration.JWTChave;
import org.financeiro.exceptions.LoginException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LoginBusiness implements ILoginBusiness {

	@Inject
	IContaBusiness contaBusiness;

	@Override
	public LoginDTO validateLogin(String token) throws LoginException {
		HttpTransport transport = new NetHttpTransport();
		GsonFactory factory = new GsonFactory() ;
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
			.setAudience(Collections.singletonList(GoogleClientId.CLIENT_ID.getValue()))
			.build();
		try {
			GoogleIdToken idToken = verifier.verify(token);
			if (idToken != null) {
				Payload payload = idToken.getPayload();
				return this.processUser(payload);
			} else {
				throw new LoginException();
			}
		} catch ( GeneralSecurityException | IOException e ) {
			e.printStackTrace();
		}
		return null;
	}

	private LoginDTO processUser(Payload payload) throws LoginException {
		Boolean emailVerified = payload.getEmailVerified();
		if (!emailVerified) {
			throw new LoginException();
		}
		Conta conta = new Conta();
		conta.setGoogleId(payload.getSubject());
		conta.setEmail(payload.getEmail());
		conta.setNome((String) payload.get("name"));
		conta.setFoto((String) payload.get("picture"));
		conta.setSobrenome((String) payload.get("family_name"));
		conta.setPrimeiroNome((String) payload.get("given_name"));
		this.contaBusiness.processAccount(conta);
		String token = this.geraToken(conta.getGoogleId());
		return new LoginDTO(conta.getFoto(), token);
	}

	private String geraToken( String googleId ) {
		Algorithm algoritmo = Algorithm.HMAC512(JWTChave.CHAVE.getValue());
		return JWT.create()
			.withClaim( "googleId", googleId )
			.sign( algoritmo );
	}
}
