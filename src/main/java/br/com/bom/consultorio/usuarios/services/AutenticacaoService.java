package br.com.bom.consultorio.usuarios.services;

import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import br.com.bom.consultorio.usuarios.payloads.requests.LoginApiRequest;
import br.com.bom.consultorio.usuarios.payloads.responses.LoginApiResponse;
import br.com.bom.consultorio.usuarios.usecases.AutenticarUsuarioUseCase;
import br.com.bom.consultorio.usuarios.usecases.dtos.AutenticarUsuarioUseCaseResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;

    public LoginApiResponse login(LoginApiRequest loginApiRequest) {
        AutenticarUsuarioUseCaseResponse autenticarUsuarioUseCaseResponse = this.autenticarUsuarioUseCase
                .executar(loginApiRequest.getEmail(), loginApiRequest.getSenha());

        LoginApiResponse response = new LoginApiResponse();
        response.setToken(autenticarUsuarioUseCaseResponse.token());
        response.setEmail(autenticarUsuarioUseCaseResponse.email());
        response.setIdentificadorUsuario(autenticarUsuarioUseCaseResponse.identificadorUsuario());

        response.setDataAutenticacao(LocalDateTime.now());
        if (!Objects.isNull(EmpresaTenantContext.getEmpresaAtual())) {
            response.setEmpresaAutenticada(EmpresaTenantContext.getEmpresaAtual().getIdentificador());
        }

        return response;
    }
}
