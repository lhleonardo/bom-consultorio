package br.com.bom.consultorio.shared.http.interceptors;

import br.com.bom.consultorio.empresa.usecases.ConsultarEmpresaPorSlugUseCase;
import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Component
@RequiredArgsConstructor
public class IdentificarEmpresaSubdominioInterceptor implements HandlerInterceptor {

    private static final String DELIMITADOR_SUBDOMINIO = ".";
    private static final String REGEX_DELIMITADOR_SUBDOMINIO = "\\.";

    private final ConsultarEmpresaPorSlugUseCase consultarEmpresaPorSlugUseCase;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String serverName = request.getServerName();

        log.info("Server name: {}", serverName);

        if (serverName.contains(DELIMITADOR_SUBDOMINIO)) {
            String subdominio = serverName.split(REGEX_DELIMITADOR_SUBDOMINIO)[0];

            this.consultarEmpresaPorSlugUseCase.executar(subdominio)
                    .ifPresent(empresaModel -> EmpresaTenantContext.setEmpresaAtual(empresaModel));
        }

        return true;
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
