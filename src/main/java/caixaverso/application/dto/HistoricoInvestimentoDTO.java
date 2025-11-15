package caixaverso.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HistoricoInvestimentoDTO (
        Long id,
        String tipo,
        BigDecimal valor,
        BigDecimal rentabilidade,
        LocalDate dataInvestimento) {}