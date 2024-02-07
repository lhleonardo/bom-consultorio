package br.com.bom.consultorio.shared.http.config;

import br.com.bom.consultorio.shared.http.consts.OpenApiConsts;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {


    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement().addList(OpenApiConsts.SECURITY_SCHEMA_NAME))
                .components(new Components().addSecuritySchemes(OpenApiConsts.SECURITY_SCHEMA_NAME, this.jwtSecurityScheme()));
    }

    public SecurityScheme jwtSecurityScheme() {
        return new SecurityScheme()
                .scheme("bearer")
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .name(OpenApiConsts.SECURITY_SCHEMA_NAME);
    }
}
