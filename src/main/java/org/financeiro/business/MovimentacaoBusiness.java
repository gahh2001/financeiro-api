package org.financeiro.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.entity.Conta;
import org.financeiro.entity.Movimentacao;
import org.financeiro.exceptions.NonExistentAccount;
import org.financeiro.repository.IMovimentacaoRepository;

@ApplicationScoped
public class MovimentacaoBusiness implements IMovimentacaoBusiness {

	@Inject
	IMovimentacaoRepository movimentacaoRepository;
	@Inject
	IContaBusiness contaBusiness;
	@Inject
	ICategoriaMovimentacaoBusiness categoriaBusiness;

	@Override
	public Movimentacao criaMovimentacao(Movimentacao movimentacao) throws NonExistentAccount {
		Conta existente = this.contaBusiness.getAccountByGoogleId(movimentacao.getGoogleId());
		if (existente == null) {
			throw new NonExistentAccount(movimentacao.getGoogleId());
		}
		Double novoSaldo;
		switch (movimentacao.getTipoMovimentacao().toUpperCase()) {
			case "NEGATIVO": {
				novoSaldo = existente.getSaldoConta() - movimentacao.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacao.getGoogleId());
				break;
			}
			default: {
				novoSaldo = existente.getSaldoConta() + movimentacao.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacao.getGoogleId());
			}
		}
		return movimentacaoRepository.criaMovimentacao(movimentacao);
	}

	@Override
	public Movimentacao atualizaMovimentacao(Movimentacao atualizada) throws NonExistentAccount {
		try {
			Double saldoAtual = contaBusiness.getAccountByGoogleId(atualizada.getGoogleId())
				.getSaldoConta();
			Double novoSaldo;
			Double valorAntigoMovimentacao = this.movimentacaoRepository.
				listaMovimentacaoPorId(atualizada.getId()).getValor();
			switch (atualizada.getTipoMovimentacao().toUpperCase()) {
				case "NEGATIVO": {
					novoSaldo = saldoAtual + valorAntigoMovimentacao - atualizada.getValor();
					contaBusiness.atualizaSaldoConta(novoSaldo, atualizada.getGoogleId());
					break;
				}
				default: {
					novoSaldo = saldoAtual - valorAntigoMovimentacao + atualizada.getValor();
					contaBusiness.atualizaSaldoConta(novoSaldo, atualizada.getGoogleId());
				}
			}
			return movimentacaoRepository.atualizaMovimentacao(atualizada);
		} catch (NullPointerException e) {
			throw new NonExistentAccount("");
		}
		
	}

	@Override
	public List<MovimentacaoDTO> listaMovimentacoesPorIdContaEPeriodo(String googleId, Long dataInicio, Long dataFim) {
		List<Movimentacao> movimentacoes = movimentacaoRepository
				.listaMovimentacoesPorIdContaEPeriodo(googleId, new Date(dataInicio), new Date (dataFim));
		return movimentacoes.stream()
			.map(movimentacao -> {
				MovimentacaoDTO dto = new MovimentacaoDTO(movimentacao);
				dto.setNomeCategoriaMovimentacao(categoriaBusiness
					.listaCategoriaMovimentacaoPorId(movimentacao.getIdCategoriaMovimentacao(), googleId)
					.getNomeCategoria());
				return dto;
			})
			.collect(Collectors.toList());
	}

	@Override
	public List<Movimentacao> listaMovimentacoesPorTipoMovimentacao(String googleId, String tipoMovimentacao,
			String dataInicio, String dataFim) {
		return movimentacaoRepository.listaMovimentacoesPorTipoMovimentacao(googleId, tipoMovimentacao,
			stringToDate(dataInicio), stringToDate(dataFim));
	};

	@Override
	public List<Movimentacao> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria, String dataInicio,
			String dataFim) {
		return movimentacaoRepository.listaMovimentacaoPorIdCategoria(googleId, idCategoria,
			stringToDate(dataInicio), stringToDate(dataFim));
	};

	@Override
	public List<Movimentacao> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria) {
		return movimentacaoRepository.listaMovimentacaoPorIdCategoria(googleId, idCategoria);
	};

	@Override
	public Boolean removeMovimentacao(Long idMovimentacao, String googleId) {
		Conta conta = this.contaBusiness.getAccountByGoogleId(googleId);
		if (conta == null) {
			return false;
		}
		Movimentacao movimentacao = movimentacaoRepository
			.listaMovimentacaoPorIdEConta(idMovimentacao, googleId);
		Double saldoAtual = conta.getSaldoConta();
		Double novoSaldo;
		switch (movimentacao.getTipoMovimentacao().toUpperCase()) {
			case "NEGATIVO": {
				novoSaldo = saldoAtual + movimentacao.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacao.getGoogleId());
				break;
			}
			default: {
				novoSaldo = saldoAtual - movimentacao.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacao.getGoogleId());
			}
		}
		return movimentacaoRepository.removeMovimentacao(idMovimentacao, googleId);
	}

	private Date stringToDate(String dataString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date teste = dateFormat.parse(dataString);
			if (teste != null) {
				return teste;
			}
			return teste;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
