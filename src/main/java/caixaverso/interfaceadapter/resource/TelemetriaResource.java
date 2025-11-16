package caixaverso.interfaceadapter.resource;

import caixaverso.application.usecase.TelemetriaUseCase;
import caixaverso.application.dto.TelemetriaResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;

@Path("/telemetria")
@Produces(MediaType.APPLICATION_JSON)
public class TelemetriaResource {

    @Inject
    TelemetriaUseCase telemetriaUseCase;

    @GET
    public List<TelemetriaResponseDTO> buscar(
            @QueryParam("inicio") String inicio,
            @QueryParam("fim") String fim) {

        LocalDate dataInicio = LocalDate.parse(inicio);
        LocalDate dataFim = LocalDate.parse(fim);

        return telemetriaUseCase.consolidadoPeriodo(dataInicio, dataFim);
    }
}
