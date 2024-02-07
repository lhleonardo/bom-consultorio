package br.com.bom.consultorio.empresa.controllers;

import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.empresa.payloads.requests.CriarEmpresaApiRequest;
import br.com.bom.consultorio.empresa.payloads.responses.CriarEmpresaApiResponse;
import br.com.bom.consultorio.empresa.payloads.responses.EmpresaApiResponse;
import br.com.bom.consultorio.empresa.services.EmpresaService;
import br.com.bom.consultorio.shared.auth.Autenticado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Autenticado
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/empresas")
@Tag(name = "Admin", description = "Gestão da plataforma para usuários administradores")
public class GestaoEmpresasAdminController {

    private final EmpresaService empresaService;

    @PostMapping()
    @Operation(
            description = "Criar uma nova empresa para a plataforma, sendo o registro centralizador de todas as outras informações do tenant.",
            summary = "Criar uma nova empresa"
    )
    public CriarEmpresaApiResponse criarNovaEmpresa(
            @RequestBody @Valid CriarEmpresaApiRequest criarEmpresaApiRequest) {

        EmpresaModel empresaModel = this.empresaService.criarEmpresa(criarEmpresaApiRequest);
        return CriarEmpresaApiResponse.fromModel(empresaModel);
    }

    @GetMapping
    @Operation(
            description = "Lista todas as empresas cadastradas na plataforma",
            summary = "Listar empresas"
    )
    public List<EmpresaApiResponse> buscarTodasEmpresas() {
        return this.empresaService.buscarEmpresasCadastradas()
                .stream()
                .map(EmpresaApiResponse::fromModel)
                .collect(Collectors.toList());
    }
}
