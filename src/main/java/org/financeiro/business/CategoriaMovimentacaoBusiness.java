package org.financeiro.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.financeiro.dto.CategoriaMovimentacaoDTO;
import org.financeiro.entity.CategoriaMovimentacao;
import org.financeiro.entity.Movimentacao;
import org.financeiro.entity.SomaCategoriasPorMesDTO;
import org.financeiro.repository.ICategoriaMovimentacaoRepository;
import org.financeiro.repository.IMovimentacaoRepository;
import org.financeiro.repository.ISomaCategoriasPorMesRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoriaMovimentacaoBusiness implements ICategoriaMovimentacaoBusiness {

	@Inject
	ICategoriaMovimentacaoRepository repository;
	@Inject
	IMovimentacaoRepository repositoryMovimentacao;
	@Inject
	ISomaCategoriasPorMesRepository somaCategoriasPorMesRepository;

	@Override
	public CategoriaMovimentacao criaCategoriaMovimentacao(CategoriaMovimentacao categoria) {
		return repository.criaCategoriaMovimentacao(categoria);
	}

	@Override
	public List<CategoriaMovimentacaoDTO> listaCategoriasMovimentacao(Long idConta) {
		List<CategoriaMovimentacao> categorias = repository.listaCategoriasMovimentacao(idConta);
		List<CategoriaMovimentacaoDTO> categoriasDTO = new ArrayList<>();
		categorias.forEach(categoria -> {
			categoriasDTO.add(new CategoriaMovimentacaoDTO(categoria.getId(), categoria.getTipoMovimentacao(),
					categoria.getNomeCategoria(), categoria.getIdConta()));
		});
		return categoriasDTO;
	}

	@Override
	public CategoriaMovimentacao listaCategoriaMovimentacaoPorId(Long idCategoria) {
		return repository.listaCategoriaMovimentacaoPorId(idCategoria);
	}

	@Override
	public List<CategoriaMovimentacao> listaCategoriasMovimentacaoPorTipoMovimentacao(String tipoMovimentacao,
			Long idconta) {
		return repository.listaCategoriasMovimentacaoPorTipoMovimentacao(tipoMovimentacao, idconta);
	}

	@Override
	public CategoriaMovimentacao removeCategoriaMovimentacao(Long idConta, Long idCategoria) {
		List<Movimentacao> movimentacoesExistentes = repositoryMovimentacao.listaMovimentacaoPorIdCategoria(idConta,
				idCategoria);
		CategoriaMovimentacao paraApagar = listaCategoriaMovimentacaoPorId(idCategoria);
		if (movimentacoesExistentes == null | movimentacoesExistentes.isEmpty()) {
			repository.removeCategoriaMovimentacao(idCategoria);
			return paraApagar;
		}
		return null;
	}

	@Override
	public CategoriaMovimentacao atualizaNomeCategoriaMovimentacao(String novoNome, Long idCategoria) {
		return repository.atualizaNomeCategoriaMovimentacao(novoNome, idCategoria);
	}

	@Override
	public List<SomaCategoriasPorMesDTO> listaCategoriasEValoresNoMes(Long idConta, Long dataMes) {
		Date data = new Date(dataMes);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);

		// primeiro dia do mês
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date inicioMes = calendar.getTime();

		// último dia do mês
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date fimMes = calendar.getTime();

		return this.somaCategoriasPorMesRepository
			.listaCategoriasEValoresNoMes(idConta, inicioMes, fimMes);
	}
}
