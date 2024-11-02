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
	public Response criaMovimentacao(Movimentacao movimentacao) {
		if (movimentacao != null) {
			try {
				Movimentacao criado = movimentacaoBusiness.criaMovimentacao(movimentacao);
				return Response.ok(criado).build();
			} catch ( NonExistentAccount e ) {}
		}
		return Response.status(400)
			.entity("Informe todos os dados corretamente").build();
	}

	@PATCH
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizaMovimentacao(Movimentacao movimentacao) {
		try {
			Movimentacao atualizada = movimentacaoBusiness.atualizaMovimentacao(movimentacao);
			return Response.ok(atualizada).build();
		} catch ( NonExistentAccount e ) {
			return Response.status(400)
				.entity("Informe todos os dados corretamente").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MovimentacaoDTO> listaMovimentacoesPorIdContaEPeriodo(
			@QueryParam("googleId") String googleId,
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim) {
		return movimentacaoBusiness.
			listaMovimentacoesPorIdContaEPeriodo(googleId, dataInicio, dataFim);
	}
	
	@GET
	@Path("/parametros")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MovimentacaoDTO> listaMovimentacoesPorParametros(
			@QueryParam("googleId") String googleId,
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim,
			@QueryParam("tipoMovimentacao") String tipo,
			@QueryParam("categorias") String categorias) {
		return movimentacaoBusiness.
			listaMovimentacoesPorParametros(googleId, dataInicio, dataFim, tipo, categorias);
	}

	@GET
	@Path("/tipoMovimentacao")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movimentacao> listaMovimentacoesPorTipoMovimentacao(
			@QueryParam("googleId") String googleId,
			@QueryParam("tipoMovimentacao") String tipoMovimentacao,
			@QueryParam("dataInicio") String dataInicio,
			@QueryParam("dataFim") String dataFim) {
		return movimentacaoBusiness.listaMovimentacoesPorTipoMovimentacao(googleId,
			tipoMovimentacao, dataInicio, dataFim);
	}

	@GET
	@Path("/categoria")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movimentacao> listaMovimentacaoPorIdCategoria(
			@QueryParam("googleId") String googleId,
			@QueryParam("idCategoria") Long idCategoria,
			@QueryParam("dataInicio") String dataInicio,
			@QueryParam("dataFim") String dataFim) {
		return movimentacaoBusiness.listaMovimentacaoPorIdCategoria(googleId,
			idCategoria, dataInicio, dataFim);
	}

	@DELETE
	@Path("/{id}")
	public Response removeMovimentacao(@PathParam(value = "id") Long idMovimentacao,
			@QueryParam("googleId") String googleId) {
		Boolean deleted = movimentacaoBusiness.removeMovimentacao(idMovimentacao, googleId);
		if (deleted) {
			return Response.ok().build();
		}
		return Response.status(500).build();
	}
}
