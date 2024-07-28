package org.financeiro.business;

import java.util.Date;

import org.financeiro.dto.MediasGeraisDTO;

public interface IMediasGeraisBusiness {

	MediasGeraisDTO obtemMediasGerais(String googleId, Date dataInicio, Date dataFim);
}
