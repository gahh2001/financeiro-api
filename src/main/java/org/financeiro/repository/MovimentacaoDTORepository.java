package org.financeiro.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.financeiro.dto.MovimentacaoDTO;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MovimentacaoDTORepository implements PanacheRepository<MovimentacaoDTO>, IMovimentacaoDTORepository{

	@Override
	@Transactional
	public List<MovimentacaoDTO> listaMovimentacoesPorParametros(String googleId, Date dataInicio, Date dataFim,
			String tipo, List<String> categorias) {
		String criterioTipo = tipo != null && !"TODOS".equals(tipo) ? "and c.tipoMovimentacao = ?4 " : "";
		String criterioCategoria = categorias != null && !categorias.isEmpty() && !categorias.contains("Todas") ? "and c.nomeCategoria in (?5)" : "";
		String query = "select new org.financeiro.dto.MovimentacaoDTO(m.id, m.valor, "
			+ "m.dataMovimentacao, m.tipoMovimentacao, "
			+ "c.id as idCategoriaMovimentacao, c.nomeCategoria, m.descricaoMovimentacao "
			+ ", c.icone, c.corIcone) from Movimentacao m join CategoriaMovimentacao c "
			+ "on m.idCategoriaMovimentacao = c.id and m.googleId = ?1 and "
			+ "m.dataMovimentacao between ?2 and ?3 "
			+ criterioTipo
			+ criterioCategoria;
		List<Object> parametros = new ArrayList<>();
		parametros.add(googleId);
		parametros.add(dataInicio);
		parametros.add(dataFim);
		if (!criterioTipo.isEmpty()) {
			parametros.add(tipo);
		}
		if (!criterioCategoria.isEmpty()) {
			parametros.add(categorias);
		}
		return list(query, parametros.toArray());
	}
}
