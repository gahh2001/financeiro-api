package org.financeiro.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.financeiro.entity.Usuario;
import org.financeiro.repository.IUsuarioRepository;

@Path("/usuario")
public class UsuarioResource {

	@Inject
	IUsuarioRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listaUsuarios() {
		return repository.listaUsuarios();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario listaUsuariosPorId(@PathParam(value = "id") Long idUsuario) {
		return repository.listaUsuarioPorId(idUsuario);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criaUsuario(Usuario usuario) {
		if (usuario.getIdConta() != null && usuario.getNome() != null && usuario.getEmail() != null
			&& usuario.getSenha() != null) {
				Usuario criado = repository.criaUsuario(usuario);
				return Response.ok(criado).build();
			}
		return Response.status(400).entity("Informe todos os dados corretamente").build();

	}

	@DELETE
	@Path("/{id}")
	public void removeUsuario(@PathParam(value = "id") Long idUsuario) {
		repository.removeUsuario(idUsuario);
	}

}
