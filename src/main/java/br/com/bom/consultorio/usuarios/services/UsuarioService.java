package br.com.bom.consultorio.usuarios.services;

import br.com.bom.consultorio.usuarios.exceptions.ConfirmacaoSenhaInvalidaException;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.payloads.requests.CriarUsuarioApiRequest;
import br.com.bom.consultorio.usuarios.usecases.CriarNovoUsuarioUseCase;
import br.com.bom.consultorio.usuarios.usecases.dtos.CriarUsuarioUseCaseRequest;
import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final CriarNovoUsuarioUseCase criarNovoUsuarioUseCase;

    /**
     * Cria um novo usuário na plataforma, vinculado diretamente para a empresa atual no contexto
     * (presente em {@link EmpresaTenantContext}). Apenas usuários com cargo ADMINISTRADOR da empresa podem cadastrar
     * novos usuários.
     *
     * @param criarUsuarioApiRequest
     * @return
     * @see EmpresaTenantContext
     */
    public UsuarioModel criarUsuario(CriarUsuarioApiRequest criarUsuarioApiRequest) {
        if (!criarUsuarioApiRequest.isSenhaConfirmada()) throw new ConfirmacaoSenhaInvalidaException();

        UsuarioModel usuarioCriado = this.criarNovoUsuarioUseCase.executar(
                new CriarUsuarioUseCaseRequest(
                        criarUsuarioApiRequest.getEmail(),
                        criarUsuarioApiRequest.getSenha(),
                        false,
                        criarUsuarioApiRequest.getNome(),
                        criarUsuarioApiRequest.getDocumento(),
                        criarUsuarioApiRequest.getTelefone()
                )
        );

        return usuarioCriado;
    }
}
