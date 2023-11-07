package br.com.bom.consultorio.shared.http.config;

import br.com.bom.consultorio.shared.http.interceptors.IdentificarEmpresaSubdominioInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final IdentificarEmpresaSubdominioInterceptor identificarEmpresaSubdominioInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.identificarEmpresaSubdominioInterceptor);
    }
}
