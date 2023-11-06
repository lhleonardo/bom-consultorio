package br.com.bom.consultorio.shared.auth;

import br.com.bom.consultorio.shared.http.consts.OpenApiConsts;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@SecurityRequirement(name = OpenApiConsts.SECURITY_SCHEMA_NAME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface Autenticado {
}
