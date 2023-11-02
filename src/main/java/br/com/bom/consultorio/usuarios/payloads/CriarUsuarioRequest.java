package br.com.bom.consultorio.usuarios.payloads;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Dados para criação de uma conta na plataforma")
public class CriarUsuarioRequest {

    @Schema(description = "Endereço de e-mail para utilizar na plataforma")
    private String email;

    @Schema(description = "Senha para acessar a plataforma")
    private String senha;

    @Schema(description = "Confirmação de senha de acesso")
    private String confirmacaoSenha;
}
