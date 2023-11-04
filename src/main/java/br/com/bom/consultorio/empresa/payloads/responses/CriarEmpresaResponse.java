package br.com.bom.consultorio.empresa.payloads.responses;

import br.com.bom.consultorio.empresa.models.EmpresaModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Schema(description = "Dados da criação de uma empresa/tenant")
public class CriarEmpresaResponse {

    @Schema(description = "Identificador UUID da empresa")
    private UUID identificador;

    @Schema(description = "Razão social da empresa")
    private String razaoSocial;

    @Schema(description = "Nome fantasia da empresa")
    private String nomeFantasia;

    @Schema(description = "Documento CNPJ da empresa")
    private String cnpj;

    @Schema(description = "Código da inscrição estadual da empresa")
    private String inscricaoEstadual;

    @Schema(description = "Data de criação da empresa")
    private OffsetDateTime dataCriacao;

    public static CriarEmpresaResponse fromModel(EmpresaModel empresaModel) {
        CriarEmpresaResponse criarEmpresaResponse = new CriarEmpresaResponse();
        criarEmpresaResponse.setCnpj(empresaModel.getCnpj());
        criarEmpresaResponse.setIdentificador(UUID.fromString(empresaModel.getIdentificador()));
        criarEmpresaResponse.setDataCriacao(empresaModel.getDataCriacao());
        criarEmpresaResponse.setNomeFantasia(empresaModel.getNomeFantasia());
        criarEmpresaResponse.setRazaoSocial(empresaModel.getRazaoSocial());
        criarEmpresaResponse.setInscricaoEstadual(empresaModel.getInscricaoEstadual());

        return criarEmpresaResponse;
    }

}