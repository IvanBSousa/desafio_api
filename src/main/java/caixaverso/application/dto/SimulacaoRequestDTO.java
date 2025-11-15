package caixaverso.application.dto;

import java.math.BigDecimal;

public record SimulacaoRequestDTO(
        Long clienteId,
        BigDecimal valorInvestido,
        Integer prazoMeses,
        String produto) {}
