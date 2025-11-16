package caixaverso.interfaceadapter.resource;

import caixaverso.application.usecase.SimulacaoUseCase;
import caixaverso.application.dto.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/simulacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SimulacaoResource {

    @Inject
    SimulacaoUseCase simulacaoUseCase;

    @POST
    @Path("/simular-investimento")
    public Response simular(SimulacaoRequestDTO request) {
        SimulacaoResponseDTO response = simulacaoUseCase.simularInterno(request);
        return Response.ok(response).build();
    }

    @GET
    public List<SimulacaoResponseDTO> listarTodos(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {
        return simulacaoUseCase.listarTodos(page, size);
    }

    @GET
    @Path("/por-produto-dia")
    public List<Object[]> listarAgregacao() {
        return simulacaoUseCase.agregacaoPorProdutoDia();
    }
}
