package org.financeiro.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.financeiro.entity.Conta;
import org.financeiro.repository.IContaRepository;

@Path("/conta")
public class ContaResource {

	@Inject
	IContaRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaContaPorId(@QueryParam(value = "googleId") String googleId) {
		Conta encontrada = repository.getAccountByGoogleId(googleId);
		if (encontrada != null) {
			return Response.ok(encontrada).build();
		}
		return Response.status(404).entity("Nenhuma conta encontrada para o ID").build();
	}

	@DELETE
	@Path("/{id}")
	public void removeConta(@PathParam(value = "id") Long idConta) {
		repository.removeConta(idConta);
	}
}
