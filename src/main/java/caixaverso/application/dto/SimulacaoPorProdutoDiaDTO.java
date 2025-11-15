package caixaverso.application.dto;

import java.math.BigDecimal;

public record SimulacaoPorProdutoDiaDTO(
        String produto,
        String data,
        Long quantidadeSimulacoes,
        BigDecimal mediaValorFinal) {}