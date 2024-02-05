package br.com.bom.consultorio.usuarios.payloads.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Dados para criação de uma conta na plataforma")
public class CriarUsuarioApiRequest {

    @Email
    @NotBlank
    @Schema(description = "Endereço de e-mail para utilizar na plataforma")
    private String email;

    @NotBlank
    @Size(min = 6)
    @Schema(description = "Senha para acessar a plataforma")
    private String senha;

    @Size(min = 6)
    @Schema(description = "Confirmação de senha de acesso")
    private String confirmacaoSenha;

    @Schema(description = "Nome completo do usuário")
    private String nome;

    @Schema(description = "Documento CPF do usuário")
    private String documento;

    @Schema(description = "Telefone para contato do usuário (sem máscara)")
    private String telefone;

    @JsonIgnore
    public boolean isSenhaConfirmada() {
        return this.senha.equals(this.confirmacaoSenha);
    }
}
