package caixaverso.interfaceadapter.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/simular-investimento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SimularInvestimentoResource {

    @POST
    public Response simularInvestimento(String request) {
        // Lógica para simular investimento será implementada aqui
        return "{\"mensagem\": \"Simulação de investimento recebida com sucesso.\"}";
    }
}
