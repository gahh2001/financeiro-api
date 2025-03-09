package org.financeiro.business;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.financeiro.dto.DesempenhoPlanejamentoDTO;
import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.dto.PlanejamentoDTO;
import org.financeiro.dto.ProgressosPlanejamentoDTO;
import org.financeiro.entity.Planejamento;
import org.financeiro.repository.IPlanejamentoRepository;
import org.financeiro.security.TokenSecurity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlanejamentoBusiness implements IPlanejamentoBusiness {

	@Inject
	IPlanejamentoRepository repository;

	@Inject
	IMovimentacaoBusiness movimentacoes;

	@Inject
	TokenSecurity tokenBusiness;

	@Override
	public Planejamento criar(String token, PlanejamentoDTO planejamento) {
		String googleId = tokenBusiness.getToken(token);
		planejamento.setGoogleId(googleId);
		return this.repository.criar(planejamento.entidade());
	}

	@Override
	public Planejamento atualizar(String token, PlanejamentoDTO planejamento) {
		String googleId = tokenBusiness.getToken(token);
		planejamento.setGoogleId(googleId);
		return this.repository.atualizar(planejamento.entidade());
	}

	@Override
	public List<PlanejamentoDTO> listarPorConta(String token) {
		String googleId = tokenBusiness.getToken(token);
		return this.repository.listarPorConta(googleId)
			.stream()
			.map(entidade -> entidade.dto())
			.toList();
	}

	@Override
	public void apagar(Long idPlanejamento) {
		this.repository.apagar(idPlanejamento);
	}

	@Override
	public ProgressosPlanejamentoDTO buscaProgressos(Long id, String token) {
		String googleId = tokenBusiness.getToken(token);
		ProgressosPlanejamentoDTO dto = new ProgressosPlanejamentoDTO();
		Planejamento planejamento = this.repository.obtemPorId(id);
		if (planejamento == null || !planejamento.getGoogleId().equals(googleId)) {
			return dto;
		}
		List<Integer> categorias = new Gson().fromJson(planejamento.getCategorias(),
			new TypeToken<List<Integer>>() {}.getType());
		Double progressoTotal = this.repository.obtemProgressoPorCategoriasEPeriodo(planejamento.getDataInicio(),
			planejamento.getDataFim(), categorias, planejamento.getGoogleId());
		Double progressoAnual = this.repository.obtemProgressoPorCategoriasEPeriodo(
			this.obtemDataInicioAnual(planejamento.getDataInicio()), this.obtemDataFimAnual(planejamento.getDataFim()),
			categorias, planejamento.getGoogleId());
		if (!planejamento.getRecorrencia().equals("ANUAL")) {
			Double progressoMensal = this.repository
				.obtemProgressoPorCategoriasEPeriodo(this.obtemDataInicioMes(planejamento.getDataInicio()),
					this.obtemDataFimMes(planejamento.getDataFim()), categorias, planejamento.getGoogleId());
			dto.setMensal(progressoMensal);
		}
		dto.setTodo(progressoTotal);
		dto.setAnual(progressoAnual);
		return dto;
	}

	@Override
	public List<DesempenhoPlanejamentoDTO> buscaDesempenho(Long id, String token) {
		String googleId = tokenBusiness.getToken(token);
		Planejamento planejamento = this.repository.obtemPorId(id);
		if (planejamento == null || !planejamento.getGoogleId().equals(googleId)) {
			return null;
		}
		return this.repository.obtemDesempenho(planejamento, this.obtemDataFimMes(planejamento.getDataFim()));
	}

	@Override
	public List<MovimentacaoDTO> buscaMovimentacoes(Long id, String token) {
		String googleId = tokenBusiness.getToken(token);
		Planejamento planejamento = this.repository.obtemPorId(id);
		if (planejamento == null || !planejamento.getGoogleId().equals(googleId)) {
			return null;
		}
		List<Integer> categorias = new Gson().fromJson(planejamento.getCategorias(),
			new TypeToken<List<Integer>>() {}.getType());
		if (categorias.contains(-1)) {
			return this.movimentacoes.listaMovimentacoesPorTipoMovimentacao(token, "POSITIVO",
				planejamento.getDataInicio().toString(), planejamento.getDataFim().toString());
		} else if (categorias.contains(-2)){
			return this.movimentacoes.listaMovimentacoesPorTipoMovimentacao(token,
				"NEGATIVO", planejamento.getDataInicio().toString(), planejamento.getDataFim().toString());
		} else {
			return categorias.stream()
				.map(categ -> this.movimentacoes
					.listaMovimentacaoPorIdCategoria(token, categ.longValue(),
						planejamento.getDataInicio().toString(),
						planejamento.getDataFim().toString()))
				.flatMap(List::stream)
				.collect(Collectors.toList());
		}
	}

	private Date obtemDataInicioAnual(Date inicio) {
		Calendar agora = Calendar.getInstance();
		Calendar inicioCalendar = Calendar.getInstance();
		inicioCalendar.setTimeInMillis(inicio.getTime());
		Integer anoAtual = (Integer) agora.get(Calendar.YEAR);
		if (inicioCalendar.before(agora)) {
			inicioCalendar.set(anoAtual, 0, 1);
		}
		return inicioCalendar.getTime();
	}

	private Date obtemDataFimAnual(Date fim) {
		Calendar agora = Calendar.getInstance();
		agora.set(Calendar.DAY_OF_MONTH, agora.getActualMaximum(Calendar.DAY_OF_MONTH));
		Calendar fimCalendar = Calendar.getInstance();
		fimCalendar.setTimeInMillis(fim.getTime());
		Integer anoAtual = (Integer) agora.get(Calendar.YEAR);
		if (fimCalendar.after(agora)) {
			fimCalendar.set(anoAtual, 11, 31);
		}
		return fimCalendar.getTime();
	}

	private Date obtemDataInicioMes(Date inicio) {
		Calendar agora = Calendar.getInstance();
		Calendar inicioCalendar = Calendar.getInstance();
		inicioCalendar.setTimeInMillis(inicio.getTime());
		if (inicioCalendar.before(agora)) {
			inicioCalendar.set(agora.get(Calendar.YEAR), agora.get(Calendar.MONTH), 1);
		}
		return inicioCalendar.getTime();
	}

	private Date obtemDataFimMes(Date fim) {
		Calendar agora = Calendar.getInstance();
		agora.set(Calendar.DAY_OF_MONTH, agora.getActualMaximum(Calendar.DAY_OF_MONTH));
		Calendar fimCalendar = Calendar.getInstance();
		fimCalendar.setTime(fim);
		if (fimCalendar.after(agora)) {
			fimCalendar.setTime(agora.getTime());
		}
		return fimCalendar.getTime();
	}
}
