package org.financeiro.repository;

import java.util.Date;
import java.util.List;

import org.financeiro.entity.Movimentacao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MovimentacaoRepository implements IMovimentacaoRepository, PanacheRepository<Movimentacao> {

	@Override
	@Transactional
	public Movimentacao criaMovimentacao(Movimentacao movimentacao) {
		persist(movimentacao);
		return movimentacao;
	}

	@Override
	@Transactional
	public Movimentacao atualizaMovimentacao(Movimentacao movimentacaoAtualizada) {
		Movimentacao movimentacaoAntiga = this.findById(movimentacaoAtualizada.getId());
		movimentacaoAntiga.setDataMovimentacao(movimentacaoAtualizada.getDataMovimentacao());
		movimentacaoAntiga.setDescricaoMovimentacao(movimentacaoAtualizada.getDescricaoMovimentacao());
		movimentacaoAntiga.setIdCategoriaMovimentacao(movimentacaoAtualizada.getIdCategoriaMovimentacao());
		movimentacaoAntiga.setValor(movimentacaoAtualizada.getValor());
		this.persistAndFlush(movimentacaoAntiga);
		return movimentacaoAtualizada;
	}

	@Override
	@Transactional
	public Movimentacao listaMovimentacaoPorIdEConta(Long id, String googleId) {
		List<Movimentacao> movimentacao = list(
			"select m from  Movimentacao where t.id = ?1 and t.idConta = ?2", id, googleId);
		return movimentacao != null && !movimentacao.isEmpty() ? movimentacao.get(0) : null;
	}

	@Override
	@Transactional
	public Movimentacao listaMovimentacaoPorId(Long id) {
		return this.findById(id);
	}

	@Override
	@Transactional
	public List<Movimentacao> listaMovimentacoesPorIdContaEPeriodo(String googleId, Date dataInicio, Date dataFim) {
		return list("select t from Movimentacao t where t.idConta = ?1 "
				+ "and (t.dataMovimentacao between ?2 and ?3)", googleId, dataInicio, dataFim);
	}

	@Override
	@Transactional
	public List<Movimentacao> listaMovimentacoesPorTipoMovimentacao(String googleId, String tipoMovimentacao,
			Date dataInicio, Date dataFim) {
		return list("select t from Movimentacao t where t.idConta = ?1 and t.tipoMovimentacao = ?2 "
				+ "and (t.dataMovimentacao between ?3 and ?4)", googleId, tipoMovimentacao, dataInicio, dataFim);
	}

	@Override
	@Transactional
	public List<Movimentacao> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria, Date dataInicio,
			Date dataFim) {
		return list("select t from Movimentacao t where t.idConta = ?1 and t.idCategoriaMovimentacao = ?2 "
				+ "and (t.dataMovimentacao between ?3 and ?4)", googleId, idCategoria, dataInicio, dataFim);
	}

	@Override
	@Transactional
	public List<Movimentacao> listaMovimentacaoPorIdCategoria(String googleId, Long idCategoria) {
		return list("select t from Movimentacao t where t.idConta = ?1 and t.idCategoriaMovimentacao = ?2",
			googleId, idCategoria);
	}

	@Override
	@Transactional
	public Boolean removeMovimentacao(Long idMovimentacao, String googleId) {
		return deleteById(idMovimentacao);
	}
}
