package br.com.bom.consultorio.empresa.usecases.convites.dtos;

import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.usuarios.enums.PerfilAcessoUsuarioEmpresaEnum;
import jakarta.validation.constraints.Email;

/**
 * Dados necessários para criar um convite de membro em uma determinada empresa.
 *
 * @param empresaModel empresa que o convite será vinculado
 * @param perfilAcesso perfil de acesso que o usuário terá na empresa ao ingressar pelo convite
 * @param email e-mail do usuário que receberá o convite
 */
public record CriarConviteUseCaseRequest
        (EmpresaModel empresaModel, PerfilAcessoUsuarioEmpresaEnum perfilAcesso, @Email String email){

}
