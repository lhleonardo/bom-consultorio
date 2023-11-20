package br.com.bom.consultorio.empresa.payloads.responses;

import br.com.bom.consultorio.empresa.models.EmpresaModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Schema(description = "Informações cadastrais de uma empresa na plataforma")
public class EmpresaApiResponse {

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

    @Schema(description = "Data de criação da empresa")
    private OffsetDateTime dataUltimaAlteracao;

    public static EmpresaApiResponse fromModel(EmpresaModel empresaModel) {
        // TODO: usar algum mapper
        EmpresaApiResponse empresaApiResponse = new EmpresaApiResponse();
        empresaApiResponse.setIdentificador(UUID.fromString(empresaModel.getIdentificador()));
        empresaApiResponse.setDataCriacao(empresaModel.getDataCriacao());
        empresaApiResponse.setNomeFantasia(empresaModel.getNomeFantasia());
        empresaApiResponse.setCnpj(empresaModel.getCnpj());
        empresaApiResponse.setDataUltimaAlteracao(empresaModel.getDataAlteracao());
        empresaApiResponse.setInscricaoEstadual(empresaModel.getInscricaoEstadual());
        empresaApiResponse.setRazaoSocial(empresaModel.getRazaoSocial());

        return empresaApiResponse;
    }
}
