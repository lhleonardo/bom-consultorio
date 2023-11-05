package br.com.bom.consultorio.shared.jwt.services;

import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import br.com.bom.consultorio.shared.jwt.env.JwtEnvironment;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final ApplicationContext applicationContext;
    private final JwtEnvironment jwtEnvironment;

    public String gerarTokenJwt(UsuarioModel usuarioModel) {
        final ZoneOffset zoneOffsetBrasil = ZoneOffset.of("-03:00");

        Instant dataCriacao = LocalDateTime.now()
                .toInstant(zoneOffsetBrasil);

        Instant dataExpiracao = LocalDateTime.now()
                .plusMinutes(30)
                .toInstant(zoneOffsetBrasil);

        return JWT.create()
                .withIssuer(applicationContext.getId())
                .withIssuedAt(dataCriacao)
                .withSubject(usuarioModel.getEmail())
                .withClaim("master", usuarioModel.isAdministradorPlataforma())
                .withClaim("tenantId", EmpresaTenantContext.getEmpresaAtual())
                .withExpiresAt(dataExpiracao)
                .sign(Algorithm.HMAC256(jwtEnvironment.getSecret()));
    }
}
