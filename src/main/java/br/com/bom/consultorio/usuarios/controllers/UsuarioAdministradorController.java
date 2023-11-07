package br.com.bom.consultorio.usuarios.controllers;

import br.com.bom.consultorio.shared.http.consts.OpenApiConsts;
import br.com.bom.consultorio.usuarios.payloads.requests.CriarUsuarioApiRequest;
import br.com.bom.consultorio.usuarios.services.UsuarioAdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios/admin")
@SecurityRequirement(name = OpenApiConsts.SECURITY_SCHEMA_NAME)
@Tag(name = "Usuários Administradores", description = "Gestão dos usuários administradores globais da plataforma")
public class UsuarioAdministradorController {

    private final UsuarioAdministradorService usuarioAdministradorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            description = "Criar um novo usuário administrador da plataforma, com permissões máximas para todas " +
                    "as empresas. Só pode ser criado por outro usuário administrador.",
            summary = "Criar um novo administrador da plataforma"
    )
    public void criarUsuarioAdministrador(@RequestBody @Valid CriarUsuarioApiRequest criarUsuarioApiRequest) {
        this.usuarioAdministradorService.criarNovoAdministrador(criarUsuarioApiRequest);
    }
}
