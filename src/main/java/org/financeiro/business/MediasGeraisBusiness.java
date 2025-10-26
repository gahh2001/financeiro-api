package org.financeiro.business;

import java.util.Date;

import org.financeiro.dto.MediasGeraisDTO;
import org.financeiro.repository.MediasGeraisRepository;
import org.financeiro.security.TokenSecurity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MediasGeraisBusiness {

	@Inject
	MediasGeraisRepository mediasRepository;

	@Inject
	TokenSecurity tokenBusiness;

	public MediasGeraisDTO obtemMediasGerais(String token, Date dataInicio, Date dataFim) {
		String googleId = tokenBusiness.getToken(token);
		MediasGeraisDTO maisGanha = this.mediasRepository
			.obtemCategoriaMaisRegistradaPorTipo(googleId, dataInicio, dataFim, "POSITIVO");
		MediasGeraisDTO maisGasta = this.mediasRepository
			.obtemCategoriaMaisRegistradaPorTipo(googleId, dataInicio, dataFim, "NEGATIVO");
		Double mediaGanhos = this.mediasRepository
			.obtemMediaMensalPorTipo(googleId, dataInicio, dataFim, "POSITIVO");
		Double mediaGastos = this.mediasRepository
			.obtemMediaMensalPorTipo(googleId, dataInicio, dataFim, "NEGATIVO");
		if (maisGanha != null && maisGasta != null) {
			return new MediasGeraisDTO(maisGanha.getCategoriaMaisGasta() , maisGasta.getCategoriaMaisGasta(),
				mediaGanhos, mediaGastos);
		}
		return new MediasGeraisDTO("... sem dados" , "... sem dados",
				mediaGanhos, mediaGastos);
	}
}
