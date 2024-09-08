package org.financeiro.repository;

import java.util.Date;

import org.financeiro.dto.MediasGeraisDTO;

public interface IMediasGeraisRepository {

	MediasGeraisDTO obtemCategoriaMaisRegistradaPorTipo(String googleId, Date dataInicio,
		Date dataFim, String tipo);
	Double obtemMediaMensalPorTipo(String googleId, Date dataInicio, Date dataFim, String tipo);
}
