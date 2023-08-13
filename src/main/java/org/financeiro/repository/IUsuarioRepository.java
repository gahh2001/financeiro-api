package org.financeiro.repository;

import java.util.List;

import org.financeiro.entity.Usuario;

public interface IUsuarioRepository {

Usuario criaUsuario(Usuario usuario);
List<Usuario> listaUsuarios();
Usuario listaUsuarioPorId(Long id);
void removeUsuario(Long id);

}
