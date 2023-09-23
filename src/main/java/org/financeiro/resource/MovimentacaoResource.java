package org.financeiro.resource;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
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

import org.financeiro.business.IMovimentacaoBusiness;
import org.financeiro.entity.Movimentacao;

@Path("/movimentacao")
@ApplicationScoped
public class MovimentacaoResource {

	@Inject
	IMovimentacaoBusiness business;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criaMovimentacao(Movimentacao movimentacao) {
		if (movimentacao.getIdConta() != null && movimentacao.getValor() != null && movimentacao.getDataMovimentacao() != null
				&& movimentacao.getTipoMovimentacao() != null && movimentacao.getIdCategoriaMovimentacao() != null) {
					Movimentacao criado = business.criaMovimentacao(movimentacao);
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
	public List<Movimentacao> listaMovimentacaosPorIdConta(
			@QueryParam("idConta") Long idConta, 
			@QueryParam("dataInicio") Long dataInicio,
			@QueryParam("dataFim") Long dataFim) {
		return business.listaMovimentacaosPorIdConta(idConta, new Date(dataInicio), new Date(dataFim));
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
	public void removeMovimentacao(@PathParam(value = "id") Long idMovimentacao) {
		business.removeMovimentacao(idMovimentacao);
	}
}
