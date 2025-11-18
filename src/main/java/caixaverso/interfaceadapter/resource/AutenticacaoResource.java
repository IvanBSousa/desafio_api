package caixaverso.interfaceadapter.resource;

import caixaverso.application.dto.LoginRequestDTO;
import caixaverso.application.usecase.UsuarioUseCase;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Map;

@Path("/api/auth")
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
@Tag(name = "Autenticação", description = "Operações para autenticação e obtenção de token JWT.")
public class AutenticacaoResource {

    private final UsuarioUseCase usuarioUseCase;

    public AutenticacaoResource(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
//    @Operation(summary = "Autentica um usuário e retorna um token JWT",
//            description = "Recebe credenciais de usuário e senha. Se forem válidas, retorna um token JWT para ser usado em endpoints protegidos.")
//    @APIResponses(value = {
//            @APIResponse(
//                    responseCode = "200",
//                    description = "Autenticação bem-sucedida. O token JWT é retornado no corpo da resposta como texto puro.",
//                    content = @Content(mediaType = MediaType.TEXT_PLAIN, schema = @Schema(implementation = String.class))
//            ),
//            @APIResponse(
//                    responseCode = "400",
//                    description = "Requisição mal formatada. O corpo da requisição não é um JSON válido.",
//                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class))
//            ),
//            @APIResponse(
//                    responseCode = "401",
//                    description = "Credenciais inválidas. Usuário ou senha incorretos.",
//                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class))
//            ),
//            @APIResponse(ref = "InternalServerError")
//    })
    public Response login(LoginRequestDTO loginRequest) {
        return usuarioUseCase.authenticateAndGenerateToken(loginRequest.usuario(), loginRequest.senha())
                .map(token -> Response.ok(token).build())
                .orElseGet(() -> Response.status(Response.Status.UNAUTHORIZED)
                        .type(MediaType.APPLICATION_JSON)
                        .entity(Map.of("error", "Credenciais inválidas.", "details", "Usuário ou senha incorretos."))
                        .build());
    }
}
