package caixaverso.infrastructure.mapper;

import caixaverso.application.dto.ResultadoSimulacaoDTO;
import caixaverso.application.dto.SimulacaoProdutoValidadoDTO;
import caixaverso.application.dto.SimulacaoResponseDTO;
import caixaverso.infrastructure.persistence.entity.SimulacaoEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimulacaoResponseMapper {

    /**
     * Converte a entidade de simulação para o DTO final da API.
     */
    public SimulacaoResponseDTO toResponse(SimulacaoEntity entity) {
        return new SimulacaoResponseDTO(
                toProdutoValidadoDTO(entity),
                toResultadoDTO(entity),
                entity.getDataSimulacao()
        );
    }

    private SimulacaoProdutoValidadoDTO toProdutoValidadoDTO(SimulacaoEntity entity) {
        return new SimulacaoProdutoValidadoDTO(
                entity.getProdutoId(),
                entity.getProduto(),
                entity.getProduto(),       // Caso exista; se não, remover
                entity.getTaxaJurosAnual(),    // rentabilidade anual
                entity.getRiscoProduto()       // risco (caso exista no entity)
        );
    }

    private ResultadoSimulacaoDTO toResultadoDTO(SimulacaoEntity entity) {
        return new ResultadoSimulacaoDTO(
                entity.getValorFinal(),
                entity.getTaxaMensalEfetiva(),
                entity.getPrazoMeses()
        );
    }
}
