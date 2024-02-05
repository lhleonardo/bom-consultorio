package br.com.bom.consultorio.usuarios.controllers;

import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.payloads.mappers.UsuarioApiResponseMapper;
import br.com.bom.consultorio.usuarios.payloads.requests.CriarUsuarioApiRequest;
import br.com.bom.consultorio.usuarios.payloads.requests.LoginApiRequest;
import br.com.bom.consultorio.usuarios.payloads.responses.CriarUsuarioApiResponse;
import br.com.bom.consultorio.usuarios.payloads.responses.LoginApiResponse;
import br.com.bom.consultorio.usuarios.services.AutenticacaoService;
import br.com.bom.consultorio.usuarios.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticação", description = "Autenticação e gestão de acesso dos usuários na plataforma")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(security = {})
    public LoginApiResponse login(@RequestBody @Valid LoginApiRequest loginApiRequest) {
        return this.autenticacaoService.login(loginApiRequest);
    }

    @PostMapping("/registro")
    @Operation(
            summary = "Criar novo usuário na plataforma",
            description = "Cria um novo usuário para possibilitar a utilização na plataforma. " +
                    "O usuário é criado sem nenhum vínculo com empresas e precisa receber um convite para ser associado a uma."
    )
    @ResponseStatus(HttpStatus.CREATED)
    public CriarUsuarioApiResponse registrarUsuario(@RequestBody @Valid CriarUsuarioApiRequest criarUsuarioApiRequest) {
        UsuarioModel usuarioModel = this.usuarioService.criarUsuario(criarUsuarioApiRequest);
        return UsuarioApiResponseMapper.modelToApiResponse(usuarioModel);
    }
}
