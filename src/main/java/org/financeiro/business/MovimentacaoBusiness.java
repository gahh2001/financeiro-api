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
import org.financeiro.repository.IMovimentacaoRepository;

@ApplicationScoped
public class MovimentacaoBusiness implements IMovimentacaoBusiness {

	@Inject
	IMovimentacaoRepository movimentacaoRepository;
	@Inject
	IContaBusiness contaBusiness;
	@Inject
	ICategoriaMovimentacaoBusiness categoriaMovimentacaobusiness;

	@Override
	public Movimentacao criaMovimentacao(Movimentacao movimentacao, String googleId) {
		Double saldoAtual = contaBusiness.listaContaPorId(movimentacao.getIdConta()).getSaldoConta();
		Double novoSaldo;
		switch (movimentacao.getTipoMovimentacao().toUpperCase()) {
			case "NEGATIVO": {
				novoSaldo = saldoAtual - movimentacao.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacao.getIdConta());
				break;
			}
			default: {
				novoSaldo = saldoAtual + movimentacao.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacao.getIdConta());
			}
		}

		return movimentacaoRepository.criaMovimentacao(movimentacao);
	}

	@Override
	public Movimentacao atualizaMovimentacao(Movimentacao movimentacaoAtualizada, String googleId) {
		if (movimentacaoAtualizada == null || movimentacaoAtualizada.getId() == null) {
			return null;
		}
		Double saldoAtual = contaBusiness.listaContaPorId(movimentacaoAtualizada.getIdConta()).getSaldoConta();
		Double novoSaldo;
		Double valorAntigoMovimentacao = this.movimentacaoRepository.
			listaMovimentacaoPorId(movimentacaoAtualizada.getId()).getValor();
		switch (movimentacaoAtualizada.getTipoMovimentacao().toUpperCase()) {
			case "NEGATIVO": {
				novoSaldo = saldoAtual + valorAntigoMovimentacao - movimentacaoAtualizada.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacaoAtualizada.getIdConta());
				break;
			}
			default: {
				novoSaldo = saldoAtual - valorAntigoMovimentacao + movimentacaoAtualizada.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacaoAtualizada.getIdConta());
			}
		}
		return movimentacaoRepository.atualizaMovimentacao(movimentacaoAtualizada);
	}

	@Override
	public List<MovimentacaoDTO> listaMovimentacoesPorIdContaEPeriodo(String googleId, Date dataInicio, Date dataFim) {
		List<Movimentacao> movimentacoes = movimentacaoRepository
				.listaMovimentacoesPorIdContaEPeriodo(googleId, dataInicio, dataFim);
		return movimentacoes.stream()
			.map(movimentacao -> {
				MovimentacaoDTO dto = new MovimentacaoDTO(movimentacao);
				dto.setNomeCategoriaMovimentacao(categoriaMovimentacaobusiness
						.listaCategoriaMovimentacaoPorId(movimentacao.getIdCategoriaMovimentacao())
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
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacao.getIdConta());
				break;
			}
			default: {
				novoSaldo = saldoAtual - movimentacao.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacao.getIdConta());
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
