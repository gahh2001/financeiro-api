package org.financeiro.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.financeiro.dto.MovimentacaoDTO;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Movimentacao extends PanacheEntity{

	private Long id;
	private Long idConta;
	private Double valor;
	@Column(columnDefinition = "DATE")
	private Date dataMovimentacao;
	private String tipoMovimentacao;
	private Long idCategoriaMovimentacao;
	
	public Movimentacao() {
	}

	public Movimentacao(Long id, Long idConta, Double valor, Date dataMovimentacao, String tipoMovimentacao,
			Long idCategoriaMovimentacao) {
		this.id = id;
		this.idConta = idConta;
		this.valor = valor;
		this.dataMovimentacao = dataMovimentacao;
		this.tipoMovimentacao = tipoMovimentacao;
		this.idCategoriaMovimentacao = idCategoriaMovimentacao;
	}

	public Movimentacao(MovimentacaoDTO movimentacaoDTO) {
		this.id = movimentacaoDTO.getId();
		this.idConta = movimentacaoDTO.getIdConta();
		this.valor = movimentacaoDTO.getValor();
		this.dataMovimentacao = new Date(movimentacaoDTO.getDataMovimentacao());
		this.tipoMovimentacao = movimentacaoDTO.getTipoMovimentacao();
		this.idCategoriaMovimentacao = movimentacaoDTO.getIdCategoriaMovimentacao();
	}

	public Long getIdConta() {
		return idConta;
	}
	public void setIdConta(Long usuario) {
		this.idConta = usuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long idMovimentacao) {
		this.id = idMovimentacao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Long getIdCategoriaMovimentacao() {
		return idCategoriaMovimentacao;
	}

	public void setIdCategoriaMovimentacao(Long idCategoriaMovimentacao) {
		this.idCategoriaMovimentacao = idCategoriaMovimentacao;
	}
	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}
	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
}