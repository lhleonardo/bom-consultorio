package br.com.bom.consultorio.empresa.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@Schema(description = "Dados necessários para cadastrar uma nova empresa/tenant")
public class CriarEmpresaRequest {

    @NotBlank
    @Schema(description = "Razão social da empresa")
    private String razaoSocial;

    @NotBlank
    @Schema(description = "Nome fantasia da empresa")
    private String nomeFantasia;

    @CNPJ
    @NotBlank
    @Schema(description = "Documento CNPJ da empresa")
    private String cnpj;

    @Schema(description = "Código da inscrição estadual da empresa")
    private String inscricaoEstadual;
}