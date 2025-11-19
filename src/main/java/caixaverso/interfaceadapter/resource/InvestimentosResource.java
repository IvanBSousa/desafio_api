package caixaverso.interfaceadapter.resource;

import caixaverso.application.usecase.InvestimentoUseCase;
import caixaverso.application.dto.InvestimentoDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/investimentos")
@Produces(MediaType.APPLICATION_JSON)
public class InvestimentosResource {

    private final InvestimentoUseCase investimentoUseCase;

    public InvestimentosResource(InvestimentoUseCase investimentoUseCase) {
        this.investimentoUseCase = investimentoUseCase;
    }

    @GET
    @Path("/{clienteId}")
    public List<InvestimentoDTO> listarPorCliente(@PathParam("clienteId") Long clienteId) {
        return investimentoUseCase.listarPorCliente(clienteId);
    }
}
