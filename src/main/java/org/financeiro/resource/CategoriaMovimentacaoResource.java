package org.financeiro.resource;

import java.util.Date;
import java.util.List;

import org.financeiro.business.ICategoriaMovimentacaoBusiness;
import org.financeiro.business.IMediasGeraisBusiness;
import org.financeiro.dto.MediasGeraisDTO;
import org.financeiro.dto.SomaCategoriasPorPeriodoDTO;
import org.financeiro.entity.CategoriaMovimentacao;
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

@Path("/categoria-movimentacao")
@ApplicationScoped
public class CategoriaMovimentacaoResource {

	@Inject
	ICategoriaMovimentacaoBusiness business;

	@Inject
	IMediasGeraisBusiness mediasBusiness;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criaCategoriaMovimentacao(@HeaderParam("Authorization") String token,
			CategoriaMovimentacao categoria) {
		if (categoria != null) {
			try {
				CategoriaMovimentacao criada = business.criaCategoriaMovimentacao(categoria);
				return Response.ok(criada).build();
			} catch ( NonExistentAccount e ) {
				return Response.status(400)
					.entity(e.getMessage()).build();
			}
		}
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorConta(
			@HeaderParam("Authorization") String token) {
		return business.listaCategoriasMovimentacaoPorConta(token);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(
			@HeaderParam("Authorization") String token,
			@PathParam(value = "id") Long idCategoria) {
		return business.listaCategoriaMovimentacaoPorId(idCategoria, token);
	}

	@GET
	@Path("/tipo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(
			@HeaderParam("Authorization") String token,
			@QueryParam("tipoMovimentacao") String tipoMovimentacao) {
		return business.listaCategoriasMovimentacaoPorTipoMovimentacao(tipoMovimentacao, token);
	}

	@GET
	@Path("/soma-categorias")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaSomaCategorias(
			@HeaderParam("Authorization") String token,
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim,
			@QueryParam("tipoMovimentacao") String tipoMovimentacao) {
		List<SomaCategoriasPorPeriodoDTO> list = business
			.listaSomaPorCategoria(token, dataInicio, dataFim, tipoMovimentacao);
		return Response.ok(list).build();
	}

	@GET
	@Path("/soma-categorias-meses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaSomaCategoriasPorMes(
			@HeaderParam("Authorization") String token,
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim,
			@QueryParam("tipoMovimentacao") String tipoMovimentacao) {
		List<SomaCategoriasPorPeriodoDTO> list = business
			.listaSomaPorCategoriaEMeses(token, dataInicio, dataFim, tipoMovimentacao);
		return Response.ok(list).build();
	}

	@GET
	@Path("/soma-tipos-meses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaSomaPorTipoEMeses(
			@HeaderParam("Authorization") String token,
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim) {
		List<SomaCategoriasPorPeriodoDTO> list = business
			.listaSomaPorTipoEMeses(token, dataInicio, dataFim);
		return Response.ok(list).build();
	}

	@PATCH
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(@HeaderParam("Authorization") String token,
			CategoriaMovimentacao novaCategoria) {
		return business.atualizaNomeCategoriaMovimentacao(novaCategoria);
	}

	@DELETE
	@Path("/{idCategoria}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCategoriaMovimentacao(@HeaderParam("Authorization") String token,
			@PathParam(value = "idCategoria") Long idCategoria) {
		CategoriaMovimentacao apagada = business.removeCategoriaMovimentacao(token, idCategoria);
		if (apagada != null) {
			return Response.ok(apagada).build();
		}
		return Response.status(406).entity("Não é possível apagar esta categoria,"
			+ " pois existem registros com ela! Você pode editar o nome dela, se preferir :)").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/soma-informacoes-gerais")
	public MediasGeraisDTO obtemMediasGeraisAnalitico(
			@HeaderParam("Authorization") String token,
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim) {
		return mediasBusiness.obtemMediasGerais(token, new Date(dataInicio), new Date(dataFim));
	}
}
