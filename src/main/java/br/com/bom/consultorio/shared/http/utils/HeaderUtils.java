package br.com.bom.consultorio.shared.http.utils;

import br.com.bom.consultorio.shared.http.consts.HeadersAplicacao;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import java.util.Optional;

@UtilityClass
public class HeaderUtils {

    private static final String BEARER_PREFIX = "Bearer ";

    public static Optional<String> extrairTokenBearer(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION))
                .map(header -> header.replace(BEARER_PREFIX, StringUtils.EMPTY));
    }

    public static Optional<String> extrairHeaderEmpresaContext(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader(HeadersAplicacao.HEADER_EMPRESA_CONTEXT));
    }
}
