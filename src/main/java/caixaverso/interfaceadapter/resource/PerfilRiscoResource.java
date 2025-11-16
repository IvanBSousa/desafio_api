package caixaverso.interfaceadapter.resource;

import caixaverso.application.usecase.PerfilRiscoUseCase;
import caixaverso.application.dto.PerfilRiscoResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/perfil-risco")
@Produces(MediaType.APPLICATION_JSON)
public class PerfilRiscoResource {

    @Inject
    PerfilRiscoUseCase perfilRiscoUseCase;

    @GET
    @Path("/{clienteId}")
    public PerfilRiscoResponseDTO obter(@PathParam("clienteId") Long clienteId) {
        return perfilRiscoUseCase.calcularPerfil(clienteId);
    }
}
