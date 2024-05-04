package org.financeiro.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.financeiro.business.ICategoriaMovimentacaoBusiness;
import org.financeiro.entity.CategoriaMovimentacao;
import org.financeiro.entity.SomaCategoriasPorPeriodoDTO;
import org.financeiro.exceptions.NonExistentAccount;

@Path("/categoria-movimentacao")
@ApplicationScoped
public class CategoriaMovimentacaoResource {

	@Inject
	ICategoriaMovimentacaoBusiness business;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criaCategoriaMovimentacao(CategoriaMovimentacao categoria) {
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
	public List<CategoriaMovimentacao> listaCategoriasMovimentacao(@QueryParam("googleId") String googleId) {
		return business.listaCategoriasMovimentacaoPorConta(googleId);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(
			@PathParam(value = "id") Long idCategoria) {
		return business.listaCategoriaMovimentacaoPorId(idCategoria);
	}

	@GET
	@Path("/tipo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(
			@QueryParam("googleId") String googleId,
			@QueryParam("tipoMovimentacao") String tipoMovimentacao) {
		return business.listaCategoriasMovimentacaoPorTipoMovimentacao(tipoMovimentacao, googleId);
	}

	@GET
	@Path("/soma-categorias")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaSomaCategorias(
			@QueryParam("googleId") String googleId,
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim,
			@QueryParam("tipoMovimentacao") String tipoMovimentacao) {
		List<SomaCategoriasPorPeriodoDTO> list = business
			.listaSomaPorCategoria(googleId, dataInicio, dataFim, tipoMovimentacao);
		return Response.ok(list).build();
	}

	@GET
	@Path("/soma-categorias-meses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaSomaCategoriasPorMes(
			@QueryParam("googleId") String googleId,
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim,
			@QueryParam("tipoMovimentacao") String tipoMovimentacao) {
		List<SomaCategoriasPorPeriodoDTO> list = business
			.listaSomaPorCategoriaEMeses(googleId, dataInicio, dataFim, tipoMovimentacao);
		return Response.ok(list).build();
	}

	@PUT
	@Path("/{idCategoria}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(String novoNome,
			@PathParam(value = "idCategoria") Long idCategoria) {
		return business.atualizaNomeCategoriaMovimentacao(novoNome, idCategoria);
	}

	@DELETE
	@Path("/{idCategoria}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCategoriaMovimentacao(@PathParam(value = "idCategoria") Long idCategoria,
			@QueryParam("googleId") String googleId) {
		CategoriaMovimentacao apagada = business.removeCategoriaMovimentacao(googleId, idCategoria);
		if (apagada != null) {
			return Response.ok(apagada).build();
		}
		return Response.status(406).entity("Não é possível apagar esta categoria,"
			+ " pois existem registros com ela! Você pode editar o nome dela, se preferir :)").build();

	}

}
