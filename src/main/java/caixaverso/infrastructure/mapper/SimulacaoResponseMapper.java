package caixaverso.infrastructure.mapper;

import caixaverso.application.dto.ResultadoSimulacaoDTO;
import caixaverso.application.dto.SimulacaoProdutoValidadoDTO;
import caixaverso.application.dto.SimulacaoResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.OffsetDateTime;

@ApplicationScoped
public class SimulacaoResponseMapper {

    public SimulacaoResponseDTO toResponse(
            SimulacaoProdutoValidadoDTO produtoValidado,
            ResultadoSimulacaoDTO resultado,
            OffsetDateTime dataSimulacao
    ) {
        return new SimulacaoResponseDTO(
                produtoValidado,
                resultado,
                dataSimulacao
        );
    }
}
