package br.com.bom.consultorio.empresa.controllers;

import br.com.bom.consultorio.empresa.models.EmpresaModel;
import br.com.bom.consultorio.empresa.payloads.requests.CriarEmpresaRequest;
import br.com.bom.consultorio.empresa.payloads.responses.CriarEmpresaResponse;
import br.com.bom.consultorio.empresa.services.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/empresas")
@Tag(name = "Empresas", description = "Gerenciar as empresas/tenants da plataforma")
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping()
    @Operation(
            description = "Criar uma nova empresa para a plataforma, sendo o registro centralizador de todas as outras informações do tenant.",
            summary = "Criar uma nova empresa"
    )
    public CriarEmpresaResponse criarNovaEmpresa(
            @RequestBody @Valid CriarEmpresaRequest criarEmpresaRequest) {

        EmpresaModel empresaModel = this.empresaService.criarEmpresa(criarEmpresaRequest);
        return CriarEmpresaResponse.fromModel(empresaModel);
    }
}
