package br.com.bom.consultorio.usuarios.payloads.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Informações da autenticação do usuário")
public class LoginApiResponse {

    @Schema(description = "Código de identificação do usuário")
    private String identificadorUsuario;

    @Schema(description = "E-mail utilizado para autenticar na plataforma")
    private String email;

    @Schema(description = "Nome do usuário")
    private String nome;

    @Schema(description = "Data que a autenticação aconteceu")
    private LocalDateTime dataAutenticacao;

    @Schema(description = "Token JWT para utilizar a plataforma")
    private String token;
}
