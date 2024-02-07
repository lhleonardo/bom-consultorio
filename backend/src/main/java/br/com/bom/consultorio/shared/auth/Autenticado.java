package br.com.bom.consultorio.shared.auth;

import br.com.bom.consultorio.shared.http.consts.OpenApiConsts;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation utilizada para configurar o security schema do OpenAPI, informando a necessidade de utilizar
 * o token JWT para o contexto atual
 */
@Retention(RetentionPolicy.RUNTIME)
@SecurityRequirement(name = OpenApiConsts.SECURITY_SCHEMA_NAME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface Autenticado {
}
