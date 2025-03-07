package org.financeiro.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.financeiro.dto.MovimentacaoDTO;
import org.financeiro.entity.CategoriaMovimentacao;
import org.financeiro.entity.Conta;
import org.financeiro.entity.Movimentacao;
import org.financeiro.exceptions.NonExistentAccount;
import org.financeiro.repository.IMovimentacaoDTORepository;
import org.financeiro.repository.IMovimentacaoRepository;
import org.financeiro.security.TokenSecurity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MovimentacaoBusiness implements IMovimentacaoBusiness {

	@Inject
	IMovimentacaoRepository movimentacaoRepository;

	@Inject
	IContaBusiness contaBusiness;

	@Inject
	ICategoriaMovimentacaoBusiness categoriaBusiness;

	@Inject
	IMovimentacaoDTORepository movimentacaoDTORepository;

	@Inject
	TokenSecurity tokenBusiness;

	@Override
	public Movimentacao criaMovimentacao(String token, Movimentacao novaMovimentacao) throws NonExistentAccount {
		String googleId = tokenBusiness.getToken(token);
		Conta conta = this.contaBusiness.getAccountByGoogleId(token);
		if (conta == null) {
			throw new NonExistentAccount(novaMovimentacao.getGoogleId());
		}
		novaMovimentacao.setGoogleId(googleId);
		if (novaMovimentacao.getAlteraSaldo()) {
			this.atualizaSaldoAoCriar(novaMovimentacao, conta);
		}
		return movimentacaoRepository.criaMovimentacao(novaMovimentacao);
	}

	@Override
	public Movimentacao atualizaMovimentacao(String token, Movimentacao atualizada) throws NonExistentAccount {
		String googleId = tokenBusiness.getToken(token);
		try {
			atualizada.setGoogleId(googleId);
			if (Boolean.TRUE.equals(atualizada.getAlteraSaldo())) {
				this.atualizaSaldoAoEditar(atualizada, token);
			}
			return movimentacaoRepository.atualizaMovimentacao(atualizada);
		} catch (NullPointerException e) {
			throw new NonExistentAccount("");
		}
	}
	
	@Override
	public List<MovimentacaoDTO> listaMovimentacoesPorIdContaEPeriodo(String token, Long dataInicio, Long dataFim) {
		String googleId = tokenBusiness.getToken(token);
		List<Movimentacao> movimentacoes = movimentacaoRepository
				.listaMovimentacoesPorIdContaEPeriodo(googleId, new Date(dataInicio), new Date (dataFim));
		return movimentacoes.stream()
			.map(movimentacao -> {
				MovimentacaoDTO dto = new MovimentacaoDTO(movimentacao);
				CategoriaMovimentacao categoria = categoriaBusiness
					.listaCategoriaMovimentacaoPorId(movimentacao.getIdCategoriaMovimentacao(), token);
				dto.setNomeCategoriaMovimentacao(categoria.getNomeCategoria());
				dto.setIcone(categoria.getIcone());
				dto.setCorIcone(categoria.getCorIcone());
				return dto; //seria bacana já buscar esses dados adicionais na propria query
			})
			.collect(Collectors.toList());
	}

	@Override
	public List<MovimentacaoDTO> listaMovimentacoesPorTipoMovimentacao(String token, String tipoMovimentacao,
			String dataInicio, String dataFim) {
		String googleId = tokenBusiness.getToken(token);
		return movimentacaoRepository.listaMovimentacoesPorTipoMovimentacao(googleId, tipoMovimentacao,
			stringToDate(dataInicio), stringToDate(dataFim))
				.stream()
				.map(movimentacao -> {
					MovimentacaoDTO dto = new MovimentacaoDTO(movimentacao);
					CategoriaMovimentacao categoria = categoriaBusiness
						.listaCategoriaMovimentacaoPorId(movimentacao.getIdCategoriaMovimentacao(), token);
					dto.setNomeCategoriaMovimentacao(categoria.getNomeCategoria());
					dto.setIcone(categoria.getIcone());
					dto.setCorIcone(categoria.getCorIcone());
					return dto;
				})
				.collect(Collectors.toList());
	};

	@Override
	public List<MovimentacaoDTO> listaMovimentacaoPorIdCategoria(String token, Long idCategoria, String dataInicio,
			String dataFim) {
		String googleId = tokenBusiness.getToken(token);
		return movimentacaoRepository.listaMovimentacaoPorIdCategoria(googleId, idCategoria,
			stringToDate(dataInicio), stringToDate(dataFim))
			.stream()
			.map(movimentacao -> {
				MovimentacaoDTO dto = new MovimentacaoDTO(movimentacao);
				CategoriaMovimentacao categoria = categoriaBusiness
					.listaCategoriaMovimentacaoPorId(movimentacao.getIdCategoriaMovimentacao(), token);
				dto.setNomeCategoriaMovimentacao(categoria.getNomeCategoria());
				dto.setIcone(categoria.getIcone());
				dto.setCorIcone(categoria.getCorIcone());
				return dto;
			})
			.collect(Collectors.toList());
	};

	@Override
	public List<Movimentacao> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria) {
		return movimentacaoRepository.listaMovimentacaoPorIdCategoria(googleId, idCategoria);
	};

	@Override
	public Boolean removeMovimentacao(Long idMovimentacao, String token) {
		String googleId = tokenBusiness.getToken(token);
		Conta conta = this.contaBusiness.getAccountByGoogleId(token);
		if (conta == null) {
			return false;
		}
		Movimentacao movimentacao = movimentacaoRepository
			.listaMovimentacaoPorIdEConta(idMovimentacao, googleId);
		Double saldoAtual = conta.getSaldoConta();
		Double novoSaldo;
		if (movimentacao.getAlteraSaldo()) {
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
		}
		return movimentacaoRepository.removeMovimentacao(idMovimentacao, googleId);
	}

	@Override
	public List<MovimentacaoDTO> listaMovimentacoesPorParametros(String token, Long dataInicio, Long dataFim,
			String tipo, String categorias) {
		String googleId = tokenBusiness.getToken(token);
		return this.movimentacaoDTORepository.listaMovimentacoesPorParametros(googleId,
			new Date(dataInicio), new Date(dataFim), tipo, Arrays.asList(categorias.split(",")));
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

	private void atualizaSaldoAoCriar(Movimentacao movimentacao, Conta conta) {
		Double novoSaldo;
		switch (movimentacao.getTipoMovimentacao().toUpperCase()) {
			case "NEGATIVO": {
				novoSaldo = conta.getSaldoConta() - movimentacao.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacao.getGoogleId());
				break;
			}
			default: {
				novoSaldo = conta.getSaldoConta() + movimentacao.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, movimentacao.getGoogleId());
			}
		}
	}

	private void atualizaSaldoAoEditar(Movimentacao atualizada, String token) {
		Movimentacao antiga = this.movimentacaoRepository.listaMovimentacaoPorId(atualizada.getId());
		if ( Objects.nonNull(antiga.getAlteraSaldo()) && Objects.nonNull(atualizada.getAlteraSaldo())
				&& Boolean.FALSE.equals(antiga.getAlteraSaldo() && Boolean.TRUE.equals(atualizada.getAlteraSaldo()))) {
			Conta conta = this.contaBusiness.getAccountByGoogleId(token);
			this.atualizaSaldoAoCriar(antiga, conta);
			return;
		}
		Double saldoAtual = contaBusiness.getAccountByGoogleId(token).getSaldoConta();
		Double novoSaldo;
		switch (atualizada.getTipoMovimentacao().toUpperCase()) {
			case "NEGATIVO": {
				novoSaldo = saldoAtual + antiga.getValor() - atualizada.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, atualizada.getGoogleId());
				break;
			}
			default: {
				novoSaldo = saldoAtual - antiga.getValor() + atualizada.getValor();
				contaBusiness.atualizaSaldoConta(novoSaldo, atualizada.getGoogleId());
			}
		}
	}
}
