package caixaverso.application.dto;

public record TelemetriaItemDTO(
        String nome,
        Long quantidadeChamadas,
        Long mediaTempoRespostaMs) {}