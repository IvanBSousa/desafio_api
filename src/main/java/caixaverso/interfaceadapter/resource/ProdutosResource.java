package caixaverso.interfaceadapter.resource;

import caixaverso.application.usecase.ProdutoUseCase;
import caixaverso.application.dto.ProdutoDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/produtos-recomendados")
@Produces(MediaType.APPLICATION_JSON)
public class ProdutosResource {

    @Inject
    ProdutoUseCase produtoUseCase;

    @GET
    @Path("/{perfil}")
    public List<ProdutoDTO> recomendar(@PathParam("perfil") String perfil) {
        return produtoUseCase.buscarProdutosPorPerfil(perfil);
    }
}
