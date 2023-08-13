package org.financeiro.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.financeiro.entity.Conta;
import org.financeiro.repository.IContaRepository;

@Path("/conta")
public class ContaResource {

	@Inject
	IContaRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Conta> listaContaPorIdUsuario(@QueryParam("idusuario") Long idUsuario) {
		return repository.listaContaPorIdUsuario(idUsuario);
	}

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
		if (conta.getIdUsuario() != null && conta.getSaldo() != null && conta.getInvestimento() != null) {
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
