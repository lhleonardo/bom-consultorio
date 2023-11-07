package br.com.bom.consultorio.shared.auth.filters;

import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import br.com.bom.consultorio.shared.jwt.dtos.DadosTokenJwtAutenticacaoDto;
import br.com.bom.consultorio.shared.jwt.services.JwtService;
import br.com.bom.consultorio.usuarios.enums.PerfilAcessoEnum;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.usecases.BuscarUsuarioPeloIdentificadorUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class AutenticacaoJwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final BuscarUsuarioPeloIdentificadorUseCase buscarUsuarioPeloIdentificadorUseCase;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Executando filtro JWT");
        Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .map(header -> header.replace("Bearer ", StringUtils.EMPTY))
                .ifPresent(token -> this.autenticarUsuarioViaTokenJwt(token, filterChain));

        filterChain.doFilter(request, response);
    }

    private void autenticarUsuarioViaTokenJwt(String tokenJwt, FilterChain filterChain) {
        DadosTokenJwtAutenticacaoDto dadosToken = this.jwtService.getDadosToken(tokenJwt);

        UsuarioModel usuarioAutenticado = this.buscarUsuarioPeloIdentificadorUseCase
                .executar(dadosToken.getIdentificadorUsuarioAutenticado())
                .orElseThrow(() -> new BadCredentialsException("Bad Credentials!"));

        if (!usuarioAutenticado.isAdministradorPlataforma() &&
                !usuarioAutenticado.possuiVinculoComEmpresa(EmpresaTenantContext.getEmpresaAtual())) {
            throw new AccessDeniedException("Usuário não tem permissão para acessar a empresa atual");
        }

        PerfilAcessoEnum perfilAcesso = usuarioAutenticado.getPerfilAcessoParaEmpresa(EmpresaTenantContext.getEmpresaAtual());

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                usuarioAutenticado.getEmail(),
                null,
                Collections.singleton(new SimpleGrantedAuthority(perfilAcesso.name()))
        );

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
