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
		List<Object> parametros = new ArrayList<>();
		parametros.add(googleId);
		parametros.add(dataInicio);
		parametros.add(dataFim);
		String criterioTipo = tipo != null && !"TODOS".equals(tipo)
			? "and c.tipoMovimentacao = ?" + (parametros.size() + 1) : "";
		if (!criterioTipo.isEmpty()) {
			parametros.add(tipo);
		}
		String criterioCategoria = categorias != null && !categorias.isEmpty() && !categorias.contains("Todas")
			? "and c.nomeCategoria in (?" + (parametros.size() + 1) + ")" : "";
		if (!criterioCategoria.isEmpty()) {
			parametros.add(categorias);
		}
		String query = "select new org.financeiro.dto.MovimentacaoDTO(m.id, m.valor, "
			+ "m.dataMovimentacao, m.tipoMovimentacao, "
			+ "c.id as idCategoriaMovimentacao, c.nomeCategoria, m.descricaoMovimentacao "
			+ ", c.icone, c.corIcone) from Movimentacao m join CategoriaMovimentacao c "
			+ "on m.idCategoriaMovimentacao = c.id and m.googleId = ?1 and "
			+ "m.dataMovimentacao between ?2 and ?3 "
			+ criterioTipo
			+ criterioCategoria;
		return list(query, parametros.toArray());
	}
}
