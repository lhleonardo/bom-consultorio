package br.com.bom.consultorio.shared.auth.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import java.util.Optional;

@UtilityClass
public class AuthenticationHeadersUtils {

    private static final String BEARER_PREFIX = "Bearer ";

    public static Optional<String> extrairTokenBearer(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION))
                .map(header -> header.replace(BEARER_PREFIX, StringUtils.EMPTY));
    }
}
