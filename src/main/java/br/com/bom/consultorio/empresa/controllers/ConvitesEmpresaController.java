package br.com.bom.consultorio.empresa.controllers;

import br.com.bom.consultorio.empresa.payloads.requests.CriarNovoConviteApiRequest;
import br.com.bom.consultorio.empresa.services.ConvitesEmpresaService;
import br.com.bom.consultorio.shared.auth.Autenticado;
import br.com.bom.consultorio.shared.http.consts.HeadersAplicacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/empresas")
@Tag(name = "Empresas", description = "Gerenciamento das informações das empresas na plataforma")
@Autenticado
public class ConvitesEmpresaController {

    private final ConvitesEmpresaService convitesEmpresaService;

    @PostMapping("/convite")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(
            summary = "Criar novo convite",
            description = "Cria um novo convite para adicionar um usuário membro na empresa",
            parameters = @Parameter(
                    name = HeadersAplicacao.HEADER_EMPRESA_CONTEXT,
                    required = true,
                    schema = @Schema(type = "string", format = "uuid"),
                    in = ParameterIn.HEADER,
                    description = "Identificador da empresa"
            )
    )
    public void convidarMembroParaEmpresa(@RequestBody @Valid CriarNovoConviteApiRequest criarNovoConviteApiRequest) {
        this.convitesEmpresaService.convidar(criarNovoConviteApiRequest);
    }

    @DeleteMapping("/convite/{codigo}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(
            summary = "Cancelar convite",
            description = "Cancela um convite criado anteriormente, não permitindo que seja utilizado para um usuário ingressar na empresa",
            parameters = @Parameter(
                    name = HeadersAplicacao.HEADER_EMPRESA_CONTEXT,
                    required = true,
                    schema = @Schema(type = "string", format = "uuid"),
                    in = ParameterIn.HEADER,
                    description = "Identificador da empresa"
            )
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelarConvite(@PathVariable(name = "codigo") UUID codigoConvite) {
        this.convitesEmpresaService.cancelar(codigoConvite);
    }

    @GetMapping("/convite")
    public void listaConvitesDaEmpresa() {
    }

}
