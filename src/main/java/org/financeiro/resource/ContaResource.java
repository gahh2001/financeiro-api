package org.financeiro.resource;

import org.financeiro.business.IContaBusiness;
import org.financeiro.entity.Conta;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/conta")
public class ContaResource {

	@Context
	ContainerRequestContext requestContext;

	@Inject
	IContaBusiness contaBusiness;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaContaPorId() {
		String googleId = (String) requestContext.getProperty("googleId");
		Conta encontrada = this.contaBusiness.getAccountByGoogleId(googleId);
		if (encontrada != null) {
			return Response.ok(encontrada).build();
		}
		return Response.status(404)
			.entity("Nenhuma conta encontrada para o ID").build();
	}

	@POST
	@Path("/editar-saldo")
	public Response editarSaldo(Conta novo) {
		String googleId = (String) requestContext.getProperty("googleId");
		Conta encontrada = this.contaBusiness.getAccountByGoogleId(googleId);
		if (encontrada == null) {
			return Response.status(404).build();
		}
		this.contaBusiness.editarSaldo(encontrada, novo.getSaldoConta());
		return Response.ok().build();
	}
}
