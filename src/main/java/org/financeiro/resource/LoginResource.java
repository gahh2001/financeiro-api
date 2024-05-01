package org.financeiro.resource;

import org.financeiro.business.ILoginBusiness;
import org.financeiro.dto.LoginDTO;
import org.financeiro.exceptions.LoginException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Path("/login")
@ApplicationScoped
@Provider
public class LoginResource {
	@Inject
	ILoginBusiness loginBusiness;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criaMovimentacao( LoginDTO login) {
		try {
			LoginDTO id = this.loginBusiness.validateLogin(login.getCredential());
			return Response.ok(id).build();
		} catch ( LoginException e ) {
			return Response.status(401).build();
		}
	}
}
