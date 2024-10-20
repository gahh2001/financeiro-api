package org.financeiro.resource;

import org.financeiro.business.IContaBusiness;
import org.financeiro.entity.Conta;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/conta")
public class ContaResource {

	@Inject
	IContaBusiness contaBusiness;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaContaPorId(@QueryParam(value = "googleId") String googleId) {
		Conta encontrada = this.contaBusiness.getAccountByGoogleId(googleId);
		if (encontrada != null) {
			return Response.ok(encontrada).build();
		}
		return Response.status(404)
			.entity("Nenhuma conta encontrada para o ID").build();
	}

	@GET
	@Path("/zerar-saldo")
	public Response zerarSaldo(@QueryParam(value = "googleId") String googleId) {
		Conta encontrada = this.contaBusiness.getAccountByGoogleId(googleId);
		if (encontrada == null) {
			return Response.status(404).build();
		}
		this.contaBusiness.zeraSaldo(encontrada);
		return Response.ok().build();
	}
}
