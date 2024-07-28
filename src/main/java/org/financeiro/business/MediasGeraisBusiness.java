package org.financeiro.business;

import java.util.Date;

import org.financeiro.dto.MediasGeraisDTO;
import org.financeiro.repository.IMediasGeraisRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MediasGeraisBusiness implements IMediasGeraisBusiness{

	@Inject
	IMediasGeraisRepository mediasRepository;

	@Override
	public MediasGeraisDTO obtemMediasGerais(String googleId, Date dataInicio, Date dataFim) {
		MediasGeraisDTO maisGanha = this.mediasRepository.obtemCategoriaMaisGanha(googleId, dataInicio, dataFim);
		MediasGeraisDTO maisGasta = this.mediasRepository.obtemCategoriaMaisGasta(googleId, dataInicio, dataFim);
		Double mediaGanhos = this.mediasRepository.obtemMediaMensalPorTipo(googleId, dataInicio, dataFim, "POSITIVO");
		Double mediaGastos = this.mediasRepository.obtemMediaMensalPorTipo(googleId, dataInicio, dataFim, "NEGATIVO");
		if (maisGanha != null && maisGasta != null) {
			return new MediasGeraisDTO(maisGanha.getCategoriaMaisGasta() , maisGasta.getCategoriaMaisGasta(),
				mediaGanhos, mediaGastos);
		}
		return new MediasGeraisDTO(null);
	}
}
