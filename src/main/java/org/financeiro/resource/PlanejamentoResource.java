package org.financeiro.resource;

import java.util.List;

import org.financeiro.business.IPlanejamentoBusiness;
import org.financeiro.dto.PlanejamentoDTO;
import org.financeiro.entity.Planejamento;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/planejamento")
@ApplicationScoped
public class PlanejamentoResource {

	@Inject
	IPlanejamentoBusiness business;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criar(PlanejamentoDTO planejamento) {
		Planejamento criado = this.business.criar(planejamento);
		return Response.ok(criado).build();
	}

	@PATCH
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(PlanejamentoDTO planejamento) {
		this.business.atualizar(planejamento);
		return Response.ok(planejamento).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaPlanejamentosPorConta(@QueryParam("googleId") String googleId) {
		List<PlanejamentoDTO> resultado = this.business.listarPorConta(googleId);
		return Response.ok(resultado).build();
	}

	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam(value = "id") Long idPlanejamento,
			@QueryParam("googleId") String googleId) {
		this.business.apagar(idPlanejamento);
		return Response.ok().build();
	}
}
