package org.financeiro.business;

import java.util.List;

import org.financeiro.dto.DesempenhoPlanejamentoDTO;
import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.dto.PlanejamentoDTO;
import org.financeiro.dto.ProgressosPlanejamentoDTO;
import org.financeiro.entity.Planejamento;

public interface IPlanejamentoBusiness {

	Planejamento criar(String token, PlanejamentoDTO planejamento);
	Planejamento atualizar(String token, PlanejamentoDTO planejamento);
	List< PlanejamentoDTO > listarPorConta(String token);
	void apagar(Long idPlanejamento);
	ProgressosPlanejamentoDTO buscaProgressos(Long id, String token);
	List<DesempenhoPlanejamentoDTO> buscaDesempenho(Long id, String token);
	List<MovimentacaoDTO> buscaMovimentacoes(Long id, String token);
}
