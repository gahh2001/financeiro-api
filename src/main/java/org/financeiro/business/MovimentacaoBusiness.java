package org.financeiro.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.entity.Movimentacao;
import org.financeiro.repository.IContaRepository;
import org.financeiro.repository.IMovimentacaoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MovimentacaoBusiness implements IMovimentacaoBusiness {

	@Inject
	IMovimentacaoRepository movimentacaoRepository;
	@Inject
	IContaRepository contaRepository;
	@Inject
	ICategoriaMovimentacaoBusiness categoriaMovimentacaobusiness;

	@Override
	public Movimentacao criaMovimentacao(Movimentacao movimentacao) {
		Double saldoAtual = contaRepository.listaContaPorId(movimentacao.getIdConta()).getSaldoConta();
		Double novoSaldo;
		switch (movimentacao.getTipoMovimentacao().toUpperCase()) {
			case "NEGATIVO": {
				novoSaldo = saldoAtual - movimentacao.getValor();
				contaRepository.atualizaSaldoConta(novoSaldo, movimentacao.getIdConta());
				break;
			}
			default: {
				novoSaldo = saldoAtual + movimentacao.getValor();
				contaRepository.atualizaSaldoConta(novoSaldo, movimentacao.getIdConta());
			}
		}

		return movimentacaoRepository.criaMovimentacao(movimentacao);
	}

	@Override
	public Movimentacao atualizaMovimentacao(Movimentacao movimentacaoAtualizada) {
		if (movimentacaoAtualizada == null || movimentacaoAtualizada.getId() == null) {
			return null;
		}
		Double saldoAtual = contaRepository.listaContaPorId(movimentacaoAtualizada.getIdConta()).getSaldoConta();
		Double novoSaldo;
		Double valorAntigoMovimentacao = this.movimentacaoRepository.
			listaMovimentacaoPorId(movimentacaoAtualizada.getId()).getValor();
		switch (movimentacaoAtualizada.getTipoMovimentacao().toUpperCase()) {
			case "NEGATIVO": {
				novoSaldo = saldoAtual + valorAntigoMovimentacao - movimentacaoAtualizada.getValor();
				contaRepository.atualizaSaldoConta(novoSaldo, movimentacaoAtualizada.getIdConta());
				break;
			}
			default: {
				novoSaldo = saldoAtual - valorAntigoMovimentacao + movimentacaoAtualizada.getValor();
				contaRepository.atualizaSaldoConta(novoSaldo, movimentacaoAtualizada.getIdConta());
			}
		}
		return movimentacaoRepository.atualizaMovimentacao(movimentacaoAtualizada);
	}

	@Override
	public Movimentacao listaMovimentacaoPorId(Long id) {
		return movimentacaoRepository.listaMovimentacaoPorId(id);
	}

	@Override
	public List<MovimentacaoDTO> listaMovimentacaosPorIdContaEPeriodo(Long idConta, Date dataInicio, Date dataFim) {
		List<Movimentacao> movimentacoes = movimentacaoRepository
				.listaMovimentacaosPorIdContaEPeriodo(idConta, dataInicio, dataFim);
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
	public List<Movimentacao> listaMovimentacaosPorTipoMovimentacao(Long idConta, String tipoMovimentacao,
			String dataInicio, String dataFim) {
		return movimentacaoRepository.listaMovimentacaosPorTipoMovimentacao(idConta, tipoMovimentacao,
				stringToDate(dataInicio), stringToDate(dataFim));
	};

	@Override
	public List<Movimentacao> listaMovimentacaoPorIdCategoria(Long idConta, Long idCategoria, String dataInicio,
			String dataFim) {
		return movimentacaoRepository.listaMovimentacaoPorIdCategoria(idConta, idCategoria,
				stringToDate(dataInicio), stringToDate(dataFim));
	};

	@Override
	public List<Movimentacao> listaMovimentacaoPorIdCategoria(Long idConta, Long idCategoria) {
		return movimentacaoRepository.listaMovimentacaoPorIdCategoria(idConta, idCategoria);
	};

	@Override
	public Boolean removeMovimentacao(Long idMovimentacao) {
		Movimentacao movimentacao = movimentacaoRepository.listaMovimentacaoPorId(idMovimentacao);
		Double saldoAtual = contaRepository.listaContaPorId(movimentacao.getIdConta()).getSaldoConta();
		Double novoSaldo;
		switch (movimentacao.getTipoMovimentacao().toUpperCase()) {
			case "NEGATIVO": {
				novoSaldo = saldoAtual + movimentacao.getValor();
				contaRepository.atualizaSaldoConta(novoSaldo, movimentacao.getIdConta());
				break;
			}
			default: {
				novoSaldo = saldoAtual - movimentacao.getValor();
				contaRepository.atualizaSaldoConta(novoSaldo, movimentacao.getIdConta());
			}
		}
		return movimentacaoRepository.removeMovimentacao(idMovimentacao);
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
