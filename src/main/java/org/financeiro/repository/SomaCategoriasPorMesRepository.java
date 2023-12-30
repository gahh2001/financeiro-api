package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.entity.SomaCategoriasPorMesDTO;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SomaCategoriasPorMesRepository
		implements PanacheRepository<SomaCategoriasPorMesDTO>, ISomaCategoriasPorMesRepository {

	@Override
	@Transactional
	public List<SomaCategoriasPorMesDTO> listaCategoriasEValoresNoMes(Long idConta, Date dataInicio, Date dataFim) {
		return list("select new org.financeiro.entity.SomaCategoriasPorMesDTO("
				+ "categoria.nomeCategoria, sum(movimentacao.valor) as somaMovimentacao, "
				+ "movimentacao.tipoMovimentacao) "
				+ "from Movimentacao movimentacao "
				+ "join CategoriaMovimentacao categoria on movimentacao.idCategoriaMovimentacao = categoria.id "
				+ "where movimentacao.dataMovimentacao between ?1 and ?2 "
				+ "group by categoria.nomeCategoria, movimentacao.tipoMovimentacao", dataInicio, dataFim);
	}
}
