package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.dto.MovimentacaoDTO;

public interface IMovimentacaoDTORepository {

	List<MovimentacaoDTO> listaMovimentacoesPorParametros(String googleId, Date dataInicio, Date dataFim,
		String tipo, List<String> categorias);
}
