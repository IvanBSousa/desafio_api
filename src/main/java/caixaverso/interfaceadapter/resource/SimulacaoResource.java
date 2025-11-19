package caixaverso.interfaceadapter.resource;

import caixaverso.application.usecase.SimulacaoUseCase;
import caixaverso.application.dto.*;
import jakarta.inject.Inject;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SimulacaoResource {

    private final SimulacaoUseCase simulacaoUseCase;

    public SimulacaoResource(SimulacaoUseCase simulacaoUseCase) {
        this.simulacaoUseCase = simulacaoUseCase;
    }

    @POST
    @Path("/simular-investimento")
    @Transactional
    public Response simular(SimulacaoRequestDTO request) {
        SimulacaoResponseDTO response = simulacaoUseCase.simular(request);
        return Response.ok(response).build();
    }

    @GET
    @Path("simulacoes")
    public List<SimulacaoHistoricoDTO> listarTodos(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {
        return simulacaoUseCase.listarTodos(page, size);
    }

    @GET
    @Path("simulacoes/por-produto-dia")
    public List<SimulacaoAgrupadaResponseDTO> listarAgregacao() {
        return simulacaoUseCase.agregacaoPorProdutoDia();
    }
}
