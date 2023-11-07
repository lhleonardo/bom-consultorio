package br.com.bom.consultorio.usuarios.services;

import br.com.bom.consultorio.usuarios.exceptions.ConfirmacaoSenhaInvalidaException;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.payloads.requests.CriarUsuarioApiRequest;
import br.com.bom.consultorio.usuarios.usecases.CriarNovoUsuarioUseCase;
import br.com.bom.consultorio.usuarios.usecases.dtos.CriarUsuarioUseCaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UsuarioAdministradorService {

    private final CriarNovoUsuarioUseCase criarNovoUsuarioUseCase;

    public UsuarioModel criarNovoAdministrador(CriarUsuarioApiRequest criarUsuarioApiRequest) {
        if (!criarUsuarioApiRequest.isSenhaConfirmada()) {
            throw new ConfirmacaoSenhaInvalidaException();
        }

        UsuarioModel usuarioCriado = this.criarNovoUsuarioUseCase.executar(
                new CriarUsuarioUseCaseRequest(
                        criarUsuarioApiRequest.getEmail(),
                        criarUsuarioApiRequest.getSenha(),
                        true,
                        criarUsuarioApiRequest.getNome(),
                        criarUsuarioApiRequest.getDocumento(),
                        criarUsuarioApiRequest.getTelefone()
                )
        );

        return usuarioCriado;
    }
}
