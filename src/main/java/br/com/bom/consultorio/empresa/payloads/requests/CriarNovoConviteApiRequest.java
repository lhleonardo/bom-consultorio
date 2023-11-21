package br.com.bom.consultorio.empresa.payloads.requests;

import br.com.bom.consultorio.usuarios.enums.PerfilAcessoUsuarioEmpresaEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Schema(description = "Dados necessários para criar um novo convite de um membro para a empresa conectada")
public class CriarNovoConviteApiRequest {

    @Email
    @Schema(description = "E-mail de quem receberá o convite")
    private String email;

    @Schema(description = "Perfil de acesso que o usuário que aceitar o convite terá na plataforma")
    private PerfilAcessoUsuarioEmpresaEnum perfilAcesso;

}
