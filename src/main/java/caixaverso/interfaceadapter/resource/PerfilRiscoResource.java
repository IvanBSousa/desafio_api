package caixaverso.interfaceadapter.resource;

import caixaverso.application.usecase.PerfilRiscoUseCase;
import caixaverso.application.dto.PerfilRiscoResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/perfil-risco")
@Produces(MediaType.APPLICATION_JSON)
public class PerfilRiscoResource {

    private final PerfilRiscoUseCase perfilRiscoUseCase;

    public PerfilRiscoResource(PerfilRiscoUseCase perfilRiscoUseCase) {
        this.perfilRiscoUseCase = perfilRiscoUseCase;
    }

    @GET
    @Path("/{clienteId}")
    public Response obter(@PathParam("clienteId") Long clienteId) {
        PerfilRiscoResponseDTO perfilRisco = perfilRiscoUseCase.calcularPerfil(clienteId);
        return Response.ok(perfilRisco).build();
    }
}
