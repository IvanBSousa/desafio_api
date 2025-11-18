package caixaverso.application.usecase;

import caixaverso.domain.repository.UsuarioRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class UsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public UsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<String> authenticateAndGenerateToken(String username, String password) {
        return usuarioRepository.findByUsername(username)
                .filter(user -> BcryptUtil.matches(password, user.getSenha()))
                .map(user -> {
                    Set<String> roles = new HashSet<>(Arrays.asList(user.getRole().split(",")));
                    return Jwt.issuer("https://caixaverso.blue.org/issuer")
                            .upn(user.getUsuario())
                            .groups(roles)
                            .expiresIn(Duration.ofHours(24))
                            .sign();
                });
    }
}
