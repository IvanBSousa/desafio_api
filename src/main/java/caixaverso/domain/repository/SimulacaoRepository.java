package caixaverso.domain.repository;

import caixaverso.application.dto.SimulacaoAgrupadaDTO;
import caixaverso.domain.entity.Simulacao;
import caixaverso.infrastructure.persistence.entity.SimulacaoEntity;

import java.util.List;

public interface SimulacaoRepository {

    SimulacaoEntity salvar(SimulacaoEntity simulacao);

    List<Simulacao> listarPaginado(int page, int size);

    List<SimulacaoAgrupadaDTO> agruparPorProdutoDia();
}
