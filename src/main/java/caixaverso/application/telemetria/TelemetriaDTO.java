package caixaverso.application.telemetria;

public record TelemetriaDTO(
        String serviceName,
        long callCount,
        long averageTime) {}
