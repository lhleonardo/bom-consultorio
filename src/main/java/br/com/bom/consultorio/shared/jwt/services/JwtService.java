package br.com.bom.consultorio.shared.jwt.services;

import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import br.com.bom.consultorio.shared.jwt.constants.CamposTokenJwt;
import br.com.bom.consultorio.shared.jwt.dtos.DadosTokenJwtAutenticacaoDto;
import br.com.bom.consultorio.shared.jwt.env.JwtEnvironment;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final ZoneOffset ZONE_OFFSET_BRASIL = ZoneOffset.of("-03:00");;

    private final ApplicationContext applicationContext;
    private final JwtEnvironment jwtEnvironment;

    public String gerarTokenJwt(UsuarioModel usuarioModel) {
        Instant dataCriacao = LocalDateTime.now()
                .toInstant(ZONE_OFFSET_BRASIL);

        Instant dataExpiracao = LocalDateTime.now()
                .plusMinutes(30)
                .toInstant(ZONE_OFFSET_BRASIL);

        return JWT.create()
                .withIssuedAt(dataCriacao)
                .withExpiresAt(dataExpiracao)
                .withSubject(usuarioModel.getIdentificador())
                .withIssuer(applicationContext.getApplicationName())
                .withClaim(CamposTokenJwt.MASTER, usuarioModel.isAdministradorPlataforma())
                .withClaim(
                        CamposTokenJwt.IDENTIFICADOR_TENANT_EMPRESA,
                        Objects.isNull(EmpresaTenantContext.getEmpresaAtual()) ?
                                null : EmpresaTenantContext.getEmpresaAtual().getIdentificador()
                )
                .sign(Algorithm.HMAC256(jwtEnvironment.getSecret()));
    }

    public DadosTokenJwtAutenticacaoDto getDadosToken(String token) {
        DecodedJWT tokenDecodificado = this.decodificarToken(token);

        return DadosTokenJwtAutenticacaoDto.builder()
                .dataExpiracao(LocalDateTime.ofInstant(tokenDecodificado.getExpiresAtAsInstant(), ZONE_OFFSET_BRASIL))
                .usuarioMaster(tokenDecodificado.getClaim(CamposTokenJwt.MASTER).asBoolean())
                .identificadorEmpresaAutenticada(tokenDecodificado.getClaim(CamposTokenJwt.IDENTIFICADOR_TENANT_EMPRESA).asString())
                .identificadorUsuarioAutenticado(tokenDecodificado.getSubject())
                .build();
    }

    private DecodedJWT decodificarToken(String token) {
        DecodedJWT tokenDecodificado = JWT
                .require(Algorithm.HMAC256(this.jwtEnvironment.getSecret()))
                .withIssuer(this.applicationContext.getApplicationName())
                .build()
                .verify(token);

        return tokenDecodificado;
    }
}
