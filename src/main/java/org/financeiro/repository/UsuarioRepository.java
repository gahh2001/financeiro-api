package org.financeiro.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.financeiro.entity.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsuarioRepository implements IUsuarioRepository, PanacheRepository<Usuario>{

	@Override
	@Transactional
	public Usuario criaUsuario(Usuario usuario) {
		persist(usuario);
		return usuario;
	}

	@Override
	@Transactional
	public List<Usuario> listaUsuarios() {
		return this.listAll();
	}

	@Override
	@Transactional
	public Usuario listaUsuarioPorId(Long id) {
		return findById(id);
	}

	@Override
	@Transactional
	public void removeUsuario(Long id) {
		deleteById(id);
	}
}
