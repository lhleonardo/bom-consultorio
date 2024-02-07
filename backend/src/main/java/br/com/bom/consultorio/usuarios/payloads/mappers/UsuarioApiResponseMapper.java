package br.com.bom.consultorio.usuarios.payloads.mappers;

import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.payloads.responses.CriarUsuarioApiResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsuarioApiResponseMapper {

    public static CriarUsuarioApiResponse modelToApiResponse(UsuarioModel usuarioModel) {
        CriarUsuarioApiResponse response = new CriarUsuarioApiResponse();
        response.setIdentificador(usuarioModel.getIdentificador());
        response.setDataCriacao(usuarioModel.getDataCriacao());
        response.setEmail(usuarioModel.getEmail());

        return response;
    }
}
