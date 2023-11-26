package br.com.bom.consultorio.usuarios.controllers;

import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.payloads.mappers.UsuarioApiResponseMapper;
import br.com.bom.consultorio.usuarios.payloads.requests.CriarUsuarioApiRequest;
import br.com.bom.consultorio.usuarios.payloads.responses.CriarUsuarioApiResponse;
import br.com.bom.consultorio.usuarios.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuários", description = "Gerencia os usuários, novos acessos e customizações dos dados de usuários da plataforma")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(
            summary = "Criar novo usuário na plataforma",
            description = "Cria um novo usuário para possibilitar a utilização na plataforma. " +
                    "O usuário é criado sem nenhum vínculo com empresas e precisa receber um convite para ser associado a uma."
    )
    @ResponseStatus(HttpStatus.CREATED)
    public CriarUsuarioApiResponse criarUsuario(CriarUsuarioApiRequest criarUsuarioApiRequest) {
        UsuarioModel usuarioModel = this.usuarioService.criarUsuario(criarUsuarioApiRequest);
        return UsuarioApiResponseMapper.modelToApiResponse(usuarioModel);
    }
}
