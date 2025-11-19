package caixaverso.interfaceadapter.resource;

import caixaverso.application.dto.TelemetriaItemDTO;
import caixaverso.infrastructure.persistence.repository.TelemetriaRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import caixaverso.application.usecase.TelemetriaUseCase;
import caixaverso.application.dto.TelemetriaPeriodoDTO;
import caixaverso.application.dto.TelemetriaResponseDTO;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Path("/telemetria")
@Produces(MediaType.APPLICATION_JSON)
public class TelemetriaResource {

    private final TelemetriaUseCase telemetriaUseCase;

    private final TelemetriaRepository telemetriaRepository;

    public TelemetriaResource(TelemetriaUseCase telemetriaUseCase, TelemetriaRepository telemetriaRepository) {
        this.telemetriaUseCase = telemetriaUseCase;
        this.telemetriaRepository = telemetriaRepository;
    }

    @GET
    public Response getTelemetria() {
        var telemetryData = telemetriaUseCase.getTelemetryData();

        List<TelemetriaItemDTO> serviceInfos = telemetryData.values().stream()
                .map(data -> new TelemetriaItemDTO(
                        data.nome(),
                        data.quantidadeChamadas(),
                        data.mediaTempoRespostaMs()))
                .toList();

        LocalDate hoje = LocalDate.now();
        TelemetriaPeriodoDTO periodo = new TelemetriaPeriodoDTO(
                hoje.with(TemporalAdjusters.firstDayOfMonth()),
                hoje.with(TemporalAdjusters.lastDayOfMonth())
        );

        TelemetriaResponseDTO response = new TelemetriaResponseDTO(serviceInfos, periodo);

        return Response.ok(response).build();
    }
}