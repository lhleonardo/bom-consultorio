package br.com.bom.consultorio.empresa.usecases.empresa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@Builder
public class CriarEmpresaUseCaseRequest {

    @NotBlank
    private String razaoSocial;

    @NotBlank
    private String nomeFantasia;

    @CNPJ
    @NotBlank
    private String cnpj;

    @NotBlank
    private String inscricaoEstadual;

    @NotBlank
    private String slug;
}
