package caixaverso.application.dto;

import java.time.Instant;

public record SimularInvestimentoResponseDTO (
        ProdutoDTO produtoValidado,
        ResultadoSimulacaoDTO resultadoSimulacao,
        Instant dataSimulacao) {}
