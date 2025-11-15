package caixaverso.application.dto;

import java.math.BigDecimal;

public record ProdutoRecomendadoDTO(
        Integer id,
        String nome,
        String tipo,
        BigDecimal rentabilidade,
        String risco) {}