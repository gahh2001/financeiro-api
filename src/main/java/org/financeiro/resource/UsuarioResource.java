package org.financeiro.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
