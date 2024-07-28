package org.financeiro.repository;

import java.util.Date;

import org.financeiro.dto.MediasGeraisDTO;

public interface IMediasGeraisRepository {

	MediasGeraisDTO obtemCategoriaMaisGanha(String googleId, Date dataInicio, Date dataFim);
	MediasGeraisDTO obtemCategoriaMaisGasta(String googleId, Date dataInicio, Date dataFim);
	Double obtemMediaMensalPorTipo(String googleId, Date dataInicio, Date dataFim, String tipo);
}
