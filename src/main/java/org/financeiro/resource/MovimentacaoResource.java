package org.financeiro.resource;

import java.util.List;

import org.financeiro.business.IMovimentacaoBusiness;
import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.entity.Movimentacao;
import org.financeiro.exceptions.NonExistentAccount;

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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/movimentacao")
@ApplicationScoped
public class MovimentacaoResource {

	@Inject
	IMovimentacaoBusiness movimentacaoBusiness;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criaMovimentacao(@HeaderParam("Authorization") String token, Movimentacao movimentacao) {
		if (movimentacao != null) {
			try {
				Movimentacao criado = movimentacaoBusiness.criaMovimentacao(token, movimentacao);
				return Response.ok(criado).build();
			} catch (NonExistentAccount e) {}
		}
		return Response.status(400)
			.entity("Informe todos os dados corretamente").build();
	}

	@PATCH
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizaMovimentacao(@HeaderParam("Authorization") String token, Movimentacao movimentacao) {
		try {
			Movimentacao atualizada = movimentacaoBusiness.atualizaMovimentacao(token, movimentacao);
			return Response.ok(atualizada).build();
		} catch (NonExistentAccount e) {
			return Response.status(400)
				.entity("Informe todos os dados corretamente").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MovimentacaoDTO> listaMovimentacoesPorIdContaEPeriodo(
			@HeaderParam("Authorization") String token,
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim) {
		return movimentacaoBusiness.
			listaMovimentacoesPorIdContaEPeriodo(token, dataInicio, dataFim);
	}
	
	@GET
	@Path("/parametros")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MovimentacaoDTO> listaMovimentacoesPorParametros(
			@HeaderParam("Authorization") String token,
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim,
			@QueryParam("tipoMovimentacao") String tipo,
			@QueryParam("categorias") String categorias) {
		return movimentacaoBusiness.
			listaMovimentacoesPorParametros(token, dataInicio, dataFim, tipo, categorias);
	}

	@GET
	@Path("/tipoMovimentacao")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MovimentacaoDTO> listaMovimentacoesPorTipoMovimentacao(
			@HeaderParam("Authorization") String token,
			@QueryParam("tipoMovimentacao") String tipoMovimentacao,
			@QueryParam("dataInicio") String dataInicio,
			@QueryParam("dataFim") String dataFim) {
		return movimentacaoBusiness.listaMovimentacoesPorTipoMovimentacao(token,
			tipoMovimentacao, dataInicio, dataFim);
	}

	@DELETE
	@Path("/{id}")
	public Response removeMovimentacao(@HeaderParam("Authorization") String token,
			@PathParam(value = "id") Long idMovimentacao) {
		Boolean deleted = movimentacaoBusiness.removeMovimentacao(idMovimentacao, token);
		if (deleted) {
			return Response.ok().build();
		}
		return Response.status(500).build();
	}
}
