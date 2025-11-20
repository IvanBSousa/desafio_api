package caixaverso.interfaceadapter.resource;

import caixaverso.application.dto.TelemetriaItemDTO;
import caixaverso.infrastructure.persistence.repository.TelemetriaRepositoryImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import caixaverso.application.dto.TelemetriaPeriodoDTO;
import caixaverso.application.dto.TelemetriaResponseDTO;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Path("/telemetria")
@Produces(MediaType.APPLICATION_JSON)
public class TelemetriaResource {

    private final TelemetriaRepositoryImpl telemetriaRepositoryImpl;

    public TelemetriaResource(TelemetriaRepositoryImpl telemetriaRepositoryImpl) {
        this.telemetriaRepositoryImpl = telemetriaRepositoryImpl;
    }

    @GET
    public Response obterTelemetria(@QueryParam("inicio") LocalDate inicio,
                                    @QueryParam("fim") LocalDate fim) {
        LocalDate hoje = LocalDate.now();
        if (inicio == null) {
            inicio = hoje.with(TemporalAdjusters.firstDayOfMonth());
        }
        if (fim == null) {
            fim = hoje.with(TemporalAdjusters.lastDayOfMonth());
        }

        TelemetriaPeriodoDTO periodo = new TelemetriaPeriodoDTO(inicio, fim);

        List<TelemetriaItemDTO> itens = telemetriaRepositoryImpl.agrupaPorServicoEPeriodo(inicio, fim);

        if (fim .isBefore(inicio)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("""
                    {
                      "mensagem": "O parâmetro 'fim' deve ser maior ou igual ao parâmetro 'inicio'."
                    }
                    """).build();
        }

        if (itens.isEmpty()) {
            return Response.status(Response.Status.OK)
                    .entity("""
                    {
                      "mensagem": "Nenhum dado de telemetria encontrado."
                    }
                    """).build();
        }

        TelemetriaResponseDTO response = new TelemetriaResponseDTO(itens, periodo);

        return Response.ok(response).build();
    }
}