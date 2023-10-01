package org.financeiro.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.financeiro.business.ICategoriaMovimentacaoBusiness;
import org.financeiro.dto.CategoriaMovimentacaoDTO;
import org.financeiro.entity.CategoriaMovimentacao;

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
