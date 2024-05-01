package org.financeiro.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.financeiro.entity.Conta;
import org.financeiro.repository.IContaRepository;

@Path("/conta")
public class ContaResource {

	@Inject
	IContaRepository repository;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaContaPorId(@PathParam(value = "id") Long idConta) {
		Conta encontrada = repository.listaContaPorId(idConta);
		if (encontrada != null) {
			return Response.ok(encontrada).build();
		}
		return Response.status(404).entity("Nenhuma conta encontrada para o ID").build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criaConta(Conta conta) {
		if (conta != null) {
			Conta contaCriada = repository.criaconta(conta);
			return Response.ok(contaCriada).build();
		}
		return Response.status(400).entity("Informe todos os dados corretamente").build();
	}

	@DELETE
	@Path("/{id}")
	public void removeConta(@PathParam(value = "id") Long idConta) {
		repository.removeConta(idConta);
	}
}
