package org.financeiro.resource;

import java.util.List;

import org.financeiro.business.IPlanejamentoBusiness;
import org.financeiro.dto.DesempenhoPlanejamentoDTO;
import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.dto.PlanejamentoDTO;
import org.financeiro.dto.ProgressosPlanejamentoDTO;
import org.financeiro.entity.Planejamento;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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
	public Response criar(@HeaderParam("Authorization") String token, PlanejamentoDTO planejamento) {
		Planejamento criado = this.business.criar(token, planejamento);
		return Response.ok(criado).build();
	}

	@PATCH
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(@HeaderParam("Authorization") String token, PlanejamentoDTO planejamento) {
		this.business.atualizar(token, planejamento);
		return Response.ok(planejamento).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaPlanejamentosPorConta(@HeaderParam("Authorization") String token) {
		List<PlanejamentoDTO> resultado = this.business.listarPorConta(token);
		return Response.ok(resultado).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/progressos")
	public Response obtemProgressos(@HeaderParam("Authorization") String token, @PathParam(value = "id") Long id) {
		ProgressosPlanejamentoDTO resultado = this.business.buscaProgressos(id, token);
		return Response.ok(resultado).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/desempenho")
	public Response obtemDesempenhos(@HeaderParam("Authorization") String token, @PathParam(value = "id") Long id) {
		List<DesempenhoPlanejamentoDTO> resultado = this.business.buscaDesempenho(id, token);
		return Response.ok(resultado).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/movimentacoes")
	public Response obtemMovimentacoes(@HeaderParam("Authorization") String token, @PathParam(value = "id") Long id) {
		List<MovimentacaoDTO> resultado = this.business.buscaMovimentacoes(id, token);
		return Response.ok(resultado).build();
	}

	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam(value = "id") Long idPlanejamento) {
		//ainda não usado
		this.business.apagar(idPlanejamento);
		return Response.ok().build();
	}
}
