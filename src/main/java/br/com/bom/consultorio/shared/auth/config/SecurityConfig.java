package br.com.bom.consultorio.shared.auth.config;

import br.com.bom.consultorio.shared.auth.filters.AutenticacaoJwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] ROTAS_PERMITIDAS = {
            "/v3/api-docs/**",
            "/v3/api-docs/swagger-config",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api/v1/auth/**"
    };

    private final AutenticacaoJwtFilter autenticacaoJwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Configurando o websecurity...");
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers(ROTAS_PERMITIDAS).permitAll();
            authorize.anyRequest().authenticated();
        });

        http.addFilterBefore(this.autenticacaoJwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
