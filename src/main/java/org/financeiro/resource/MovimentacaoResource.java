package org.financeiro.resource;

import java.util.Date;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.financeiro.business.IMovimentacaoBusiness;
import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.entity.Movimentacao;

@Path("/movimentacao")
@ApplicationScoped
public class MovimentacaoResource {

	@Inject
	IMovimentacaoBusiness business;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criaMovimentacao(MovimentacaoDTO movimentacao) {
		if (movimentacao != null) {
			Movimentacao criado = business.criaMovimentacao(new Movimentacao(movimentacao));
			return Response.ok(criado).build();
		}
		return Response.status(400).entity("Informe todos os dados corretamente").build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movimentacao listaMovimentacaosPorId(@PathParam(value = "id") Long idMovimentacao) {
		return business.listaMovimentacaoPorId(idMovimentacao);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MovimentacaoDTO> listaMovimentacaosPorIdContaEPeriodo(
			@QueryParam("idConta") Long idConta,
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim) {
		return business.listaMovimentacaosPorIdContaEPeriodo(idConta, new Date(dataInicio), new Date(dataFim));
	}

	@GET
	@Path("/tipoMovimentacao")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movimentacao> listaMovimentacaosPorTipoMovimentacao(
			@QueryParam("idConta") Long idConta,
			@QueryParam("tipoMovimentacao") String tipoMovimentacao,
			@QueryParam("dataInicio") String dataInicio,
			@QueryParam("dataFim") String dataFim) {
		return business.listaMovimentacaosPorTipoMovimentacao(idConta, tipoMovimentacao, dataInicio, dataFim);
	}

	@GET
	@Path("/categoria")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movimentacao> listaMovimentacaoPorIdCategoria(
			@QueryParam("idConta") Long idConta,
			@QueryParam("idCategoria") Long idCategoria,
			@QueryParam("dataInicio") String dataInicio,
			@QueryParam("dataFim") String dataFim) {
		return business.listaMovimentacaoPorIdCategoria(idConta, idCategoria, dataInicio, dataFim);
	}

	@DELETE
	@Path("/{id}")
	public Response removeMovimentacao(@PathParam(value = "id") Long idMovimentacao) {
		Boolean deleted = business.removeMovimentacao(idMovimentacao);
		if (deleted) {
			return Response.ok().build();
		}
		return Response.status(500).build();
	}
}
