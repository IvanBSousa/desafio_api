//package caixaverso.interfaceadapter.resource;
//
//import caixaverso.application.usecase.TelemetriaUseCase;
//import caixaverso.application.dto.TelemetriaResponseDTO;
//import jakarta.inject.Inject;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import java.time.LocalDate;
//import java.util.List;
//
//@Path("/telemetria")
//@Produces(MediaType.APPLICATION_JSON)
//public class TelemetriaResource {
//
//    @Inject
//    TelemetriaUseCase telemetriaUseCase;
//
//    @GET
//    public List<TelemetriaResponseDTO> buscar(
//            @QueryParam("inicio") String inicio,
//            @QueryParam("fim") String fim) {
//
//        LocalDate dataInicio = LocalDate.parse(inicio);
//        LocalDate dataFim = LocalDate.parse(fim);
//
//        return telemetriaUseCase.consolidadoTelemetriaPeriodoDTO(dataInicio, dataFim);
//    }
//}
package caixaverso.interfaceadapter.resource;

import caixaverso.application.dto.TelemetriaItemDTO;
import caixaverso.infrastructure.persistence.repository.TelemetriaRepository;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import caixaverso.application.telemetria.TelemetriaService;
import caixaverso.application.dto.TelemetriaPeriodoDTO;
import caixaverso.application.dto.TelemetriaResponseDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Path("/telemetria")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Telemetria", description = "Operações para consultar dados de telemetria da aplicação.")
@RequestScoped
public class TelemetriaResource {

    private final TelemetriaService telemetriaService;

    private final TelemetriaRepository telemetriaRepository;

    public TelemetriaResource(TelemetriaService telemetriaService, TelemetriaRepository telemetriaRepository) {
        this.telemetriaService = telemetriaService;
        this.telemetriaRepository = telemetriaRepository;
    }

    @GET
    //@RolesAllowed("Admin")
//    @Operation(summary = "Retorna dados de telemetria dos serviços monitorados",
//            description = "Fornece um resumo do volume de chamadas e tempo médio de resposta para cada serviço. Requer permissão de Administrador.")
//    @APIResponses(value = {
//            @APIResponse(
//                    responseCode = "200",
//                    description = "Dados de telemetria retornados com sucesso.",
//                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TelemetriaResponseDTO.class))
//            ),
//            @APIResponse(ref = "Unauthorized"),
//            @APIResponse(ref = "Forbidden"),
//            @APIResponse(ref = "InternalServerError")
//    })
    public Response getTelemetry() {
        var telemetryData = telemetriaService.getTelemetryData();

        List<TelemetriaItemDTO> serviceInfos = telemetryData.values().stream()
                .map(data -> new TelemetriaItemDTO(
                        data.nome(),
                        data.quantidadeChamadas(),
                        data.mediaTempoRespostaMs()))
                .toList();

        LocalDate today = LocalDate.now();
        TelemetriaPeriodoDTO periodo = new TelemetriaPeriodoDTO(
                today.with(TemporalAdjusters.firstDayOfMonth()),
                today.with(TemporalAdjusters.lastDayOfMonth())
        );

        TelemetriaResponseDTO response = new TelemetriaResponseDTO(serviceInfos, periodo);

        return Response.ok(response).build();
    }
}
