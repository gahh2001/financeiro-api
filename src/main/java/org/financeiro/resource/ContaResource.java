package org.financeiro.resource;

import org.financeiro.business.IContaBusiness;
import org.financeiro.entity.Conta;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/conta")
public class ContaResource {

	@Inject
	IContaBusiness contaBusiness;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaContaPorId(@HeaderParam("Authorization") String token) {
		Conta encontrada = this.contaBusiness.getAccountByGoogleId(token);
		if (encontrada != null) {
			return Response.ok(encontrada).build();
		}
		return Response.status(404)
			.entity("Nenhuma conta encontrada para o ID").build();
	}

	@POST
	@Path("/editar-saldo")
	public Response editarSaldo(@HeaderParam("Authorization") String token, Conta novo) {
		Conta encontrada = this.contaBusiness.getAccountByGoogleId(token);
		if (encontrada == null) {
			return Response.status(404).build();
		}
		this.contaBusiness.editarSaldo(encontrada, novo.getSaldoConta());
		return Response.ok().build();
	}
}
