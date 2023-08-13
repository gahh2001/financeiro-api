package org.financeiro.business;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.financeiro.entity.Movimentacao;
import org.financeiro.repository.IContaRepository;
import org.financeiro.repository.IMovimentacaoRepository;

@ApplicationScoped
public class MovimentacaoBusiness implements IMovimentacaoBusiness{

	@Inject
	IMovimentacaoRepository movimentacaoRepository;
	@Inject
	IContaRepository contaRepository;

	@Override
	public Movimentacao criaMovimentacao(Movimentacao movimentacao) {
		Double saldoAtual = contaRepository.listaContaPorId(movimentacao.getIdConta()).getSaldo();
		Double investimentoAtual = contaRepository.listaContaPorId(movimentacao.getIdConta()).getInvestimento();
		Double novoSaldo;
		Double novoInvestimento;
		switch(movimentacao.getTipoMovimentacao().toUpperCase()) {
			case "INVESTIMENTO": {
				novoSaldo = saldoAtual - movimentacao.getValor();
				novoInvestimento = investimentoAtual + movimentacao.getValor();
				contaRepository.atualizaSaldoConta(novoSaldo, movimentacao.getIdConta());
				contaRepository.atualizaInvestimento(novoInvestimento, movimentacao.getIdConta());
				break;
			}
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
	public Movimentacao listaMovimentacaoPorId(Long id) {
		return movimentacaoRepository.listaMovimentacaoPorId(id);
	}

	@Override
	public List<Movimentacao> listaMovimentacaosPorIdConta(Long idConta, Long dataInicio, Long dataFim) {
		return movimentacaoRepository.listaMovimentacaosPorIdConta(idConta, dataInicio, dataFim);
	}

	@Override
	public List<Movimentacao> listaMovimentacaosPorTipoMovimentacao(Long idConta, String tipoMovimentacao, Long dataInicio, Long dataFim) {
		return movimentacaoRepository.listaMovimentacaosPorTipoMovimentacao(idConta, tipoMovimentacao, dataInicio, dataFim);
	};

	@Override
	public List<Movimentacao> listaMovimentacaoPorIdCategoria(Long idConta, Long idCategoria, Long dataInicio, Long dataFim) {
		return movimentacaoRepository.listaMovimentacaoPorIdCategoria(idConta, idCategoria, dataInicio, dataFim);
	};

	@Override
	public List<Movimentacao> listaMovimentacaoPorIdCategoria(Long idConta, Long idCategoria) {
		return movimentacaoRepository.listaMovimentacaoPorIdCategoria(idConta, idCategoria);
	};

	@Override
	public void removeMovimentacao(Long idMovimentacao) {
		movimentacaoRepository.removeMovimentacao(idMovimentacao);
	}
}
