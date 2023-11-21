package br.com.bom.consultorio.shared.auth.filters;

import br.com.bom.consultorio.shared.auth.services.UsuarioAutenticadoService;
import br.com.bom.consultorio.shared.http.utils.HeaderUtils;
import br.com.bom.consultorio.shared.jwt.dtos.DadosTokenJwtAutenticacaoDto;
import br.com.bom.consultorio.shared.jwt.services.JwtService;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.usecases.BuscarUsuarioPeloIdentificadorUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@Component
@RequiredArgsConstructor
public class AutenticacaoJwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioAutenticadoService usuarioAutenticadoService;
    private final BuscarUsuarioPeloIdentificadorUseCase buscarUsuarioPeloIdentificadorUseCase;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HeaderUtils.extrairTokenBearer(request).ifPresent(this::autenticarUsuarioViaTokenJwt);
        filterChain.doFilter(request, response);
    }


    /**
     * Cria escopo de autenticação da plataforma a partir do token JWT recebido na requisição.
     * Monta também as permissões de acesso do usuário no sistema. Se for um usuário administrador da plataforma, então
     * adiciona uma role 'ROOT' ao contexto.
     *
     * @param tokenJwt
     */
    private void autenticarUsuarioViaTokenJwt(String tokenJwt) {
        DadosTokenJwtAutenticacaoDto dadosToken = this.jwtService.getDadosToken(tokenJwt);

        UsuarioModel usuarioModel = this.buscarUsuarioPeloIdentificadorUseCase
                .executar(dadosToken.getIdentificadorUsuarioAutenticado())
                .orElseThrow(() -> new BadCredentialsException("Bad Credentials!"));

        // para usuários ROOT, terão duas permissões: ADMINISTRADOR e ROOT.
        Set<SimpleGrantedAuthority> permissoes = new HashSet<>();

        if (usuarioModel.isAdministradorPlataforma()) {
            permissoes.add(new SimpleGrantedAuthority("ROOT"));
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                usuarioModel, null, permissoes
        );

        this.usuarioAutenticadoService.setUsuarioLogado(usernamePasswordAuthenticationToken);
    }
}
