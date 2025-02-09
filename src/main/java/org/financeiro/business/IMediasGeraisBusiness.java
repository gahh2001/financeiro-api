package org.financeiro.business;

import java.util.Date;

import org.financeiro.dto.MediasGeraisDTO;

public interface IMediasGeraisBusiness {

	MediasGeraisDTO obtemMediasGerais(String token, Date dataInicio, Date dataFim);
}
