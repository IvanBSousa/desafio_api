package caixaverso.application.dto;

import java.math.BigDecimal;

public record SimularInvestimentoRequestDTO(
    Long clienteId,
    BigDecimal valor,
    Integer prazoMeses,
    String tipoProduto) {}