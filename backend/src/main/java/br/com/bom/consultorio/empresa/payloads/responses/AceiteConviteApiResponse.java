package br.com.bom.consultorio.empresa.payloads.responses;

import br.com.bom.consultorio.usuarios.enums.PerfilAcessoUsuarioEmpresaEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Dados de confirmação de aceite do convite")
public class AceiteConviteApiResponse {

    @Schema(description = "Identificador do usuário que realizou o aceite")
    private String usuario;

    @Schema(description = "Identificador da empresa que gerou o convite")
    private String empresa;

    @Schema(description = "Perfil de acesso do usuário na empresa após confirmar o convite")
    private PerfilAcessoUsuarioEmpresaEnum perfil;
}
