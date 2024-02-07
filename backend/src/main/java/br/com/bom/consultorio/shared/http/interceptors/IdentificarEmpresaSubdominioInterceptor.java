package br.com.bom.consultorio.shared.http.interceptors;

import br.com.bom.consultorio.empresa.exceptions.IdentificadorEmpresaInvalidoException;
import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.empresa.usecases.empresa.ConsultarEmpresaPorIdentificadorUseCase;
import br.com.bom.consultorio.shared.auth.services.UsuarioAutenticadoService;
import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import br.com.bom.consultorio.shared.http.utils.HeaderUtils;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class IdentificarEmpresaSubdominioInterceptor implements HandlerInterceptor {

    private final ConsultarEmpresaPorIdentificadorUseCase consultarEmpresaPorIdentificadorUseCase;
    private final UsuarioAutenticadoService usuarioAutenticadoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HeaderUtils.extrairHeaderEmpresaContext(request).ifPresent(this::configuraContextoEmpresaInformada);
        return true;
    }

    private void configuraContextoEmpresaInformada(String identificador) {
        EmpresaModel empresaModel = this.consultarEmpresaPorIdentificadorUseCase.executar(identificador)
                .orElseThrow(IdentificadorEmpresaInvalidoException::new);
        EmpresaTenantContext.setEmpresaAtual(empresaModel);
        this.atualizaPermissoesUsuarioParaEmpresaAtual(empresaModel);
    }

    private void atualizaPermissoesUsuarioParaEmpresaAtual(EmpresaModel empresaModel) {
        UsuarioModel usuarioAutenticado = this.usuarioAutenticadoService.getUsuarioAutenticado();
        Authentication authentication = this.usuarioAutenticadoService.getAuthentication();

        boolean isAdministrador = usuarioAutenticado.isAdministradorPlataforma();
        boolean usuarioEhMembroDaEmpresaAtual = usuarioAutenticado.possuiVinculoComEmpresa(EmpresaTenantContext.getEmpresaAtual());

        if (!isAdministrador && !usuarioEhMembroDaEmpresaAtual) {
            // TODO: Criar exception para esse caso
            throw new AccessDeniedException("Usuário não tem permissão para acessar a empresa");
        }

        List<GrantedAuthority> authorities = new ArrayList<>(authentication.getAuthorities());
        usuarioAutenticado.getPerfilAcessoParaEmpresa(empresaModel)
                .ifPresent(perfilAcesso -> authorities.add(new SimpleGrantedAuthority(perfilAcesso.name())));

        Authentication authenticationNovo = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), null, authorities);
        this.usuarioAutenticadoService.setUsuarioLogado(authenticationNovo);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        EmpresaTenantContext.limpar();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        EmpresaTenantContext.limpar();
    }
}
