package caixaverso.application.dto;

import java.time.OffsetDateTime;

public record SimulacaoResponseDTO(
        SimulacaoProdutoValidadoDTO produtoValidado,
        ResultadoSimulacaoDTO resultadoSimulacao,
        OffsetDateTime dataSimulacao) {}