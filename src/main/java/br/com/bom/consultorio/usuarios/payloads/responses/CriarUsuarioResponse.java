package br.com.bom.consultorio.usuarios.payloads.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Schema(description = "Dados da criação do usuário")
public class CriarUsuarioResponse {

    @Schema(description = "E-mail de acesso à plataforma")
    private String email;

    @Schema(description = "Código de identificação única do usuário")
    private String identificador;

    @Schema(description = "Data de criação do usuário")
    private OffsetDateTime dataCriacao;


}
