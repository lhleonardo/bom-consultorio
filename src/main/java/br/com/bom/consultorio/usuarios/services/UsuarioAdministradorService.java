package br.com.bom.consultorio.usuarios.services;

import br.com.bom.consultorio.usuarios.exceptions.ConfirmacaoSenhaInvalidaException;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.payloads.CriarUsuarioRequest;
import br.com.bom.consultorio.usuarios.usecases.CriarNovoUsuarioUseCase;
import br.com.bom.consultorio.usuarios.usecases.dtos.CriarUsuarioDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UsuarioAdministradorService {

    private final CriarNovoUsuarioUseCase criarNovoUsuarioUseCase;

    public UsuarioModel criarNovoAdministrador(CriarUsuarioRequest criarUsuarioRequest) {

        String senha = criarUsuarioRequest.getSenha();
        String confirmacaoSenha = criarUsuarioRequest.getConfirmacaoSenha();

        if (!senha.equals(confirmacaoSenha)) {
            throw new ConfirmacaoSenhaInvalidaException();
        }

        UsuarioModel usuarioCriado = this.criarNovoUsuarioUseCase.executar(
                new CriarUsuarioDto(criarUsuarioRequest.getEmail(), criarUsuarioRequest.getSenha(), true)
        );

        return usuarioCriado;
    }
}
