package org.financeiro.resource;

import java.util.List;

import org.financeiro.business.ICategoriaMovimentacaoBusiness;
import org.financeiro.dto.CategoriaMovimentacaoDTO;
import org.financeiro.entity.CategoriaMovimentacao;
import org.financeiro.entity.SomaCategoriasPorMesDTO;

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
			CategoriaMovimentacao criada = business.criaCategoriaMovimentacao(categoria);
			return Response.ok(criada).build();
		}
		return Response.status(400).entity("Informe todos os dados corretamente").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoriaMovimentacaoDTO> listaCategoriasMovimentacao(@QueryParam("idConta") Long idConta) {
		return business.listaCategoriasMovimentacao(idConta);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(@PathParam(value = "id") Long idCategoria) {
		return business.listaCategoriaMovimentacaoPorId(idCategoria);
	}

	@GET
	@Path("/tipo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(
			@QueryParam("idConta") Long idConta, @QueryParam("tipoMovimentacao") String tipoMovimentacao) {
		return business.listaCategoriasMovimentacaoPorTipoMovimentacao(tipoMovimentacao, idConta);
	}

	@GET
	@Path("/mes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SomaCategoriasPorMesDTO> listaCategoriasEValoresNoMes(
			@QueryParam("idConta") Long idConta, @QueryParam("dataMes") Long dataMes) {
		return business.listaCategoriasEValoresNoMes(idConta, dataMes);
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
			@QueryParam("idConta") Long idConta) {
		CategoriaMovimentacao apagada = business.removeCategoriaMovimentacao(idConta, idCategoria);
		if (apagada != null) {
			return Response.ok(apagada).build();
		}
		return Response.status(406).entity("Não é possível apagar esta categoria,"
				+ " pois existem registros com ela! Você pode editar o nome dela, se preferir :)").build();

	}

}
