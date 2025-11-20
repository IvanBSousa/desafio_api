package caixaverso.interfaceadapter.resource;

import caixaverso.application.usecase.SimulacaoUseCase;
import caixaverso.application.dto.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
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
    public Response simular(@Valid SimulacaoRequestDTO request) {
        SimulacaoResponseDTO response = simulacaoUseCase.simular(request);
        return Response.ok(response).build();
    }

    @GET
    @Path("simulacoes")
    public Response listarTodos(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {
        List<SimulacaoHistoricoDTO> simulacoes = simulacaoUseCase.listarTodos(page, size);
        if (simulacoes.isEmpty()) {
            return Response.status(Response.Status.OK)
                    .entity("""
                    {
                      "mensagem": "Nenhuma simulação encontrada."
                    }
                    """).build();
        }
        return Response.ok(simulacoes).build();
    }

    @GET
    @Path("simulacoes/por-produto-dia")
    public Response listarAgregacao() {
        List<SimulacaoAgrupadaResponseDTO> agregacoes = simulacaoUseCase.agregacaoPorProdutoDia();
        if (agregacoes.isEmpty()) {
            return Response.status(Response.Status.OK)
                    .entity("""
                    {
                      "mensagem": "Nenhuma simulação encontrada."
                    }
                    """).build();
        }
        return Response.ok(agregacoes).build();
    }
}
