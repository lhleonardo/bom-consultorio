package br.com.bom.consultorio.usuarios.services;

import br.com.bom.consultorio.usuarios.payloads.requests.LoginApiRequest;
import br.com.bom.consultorio.usuarios.payloads.responses.LoginApiResponse;
import br.com.bom.consultorio.usuarios.usecases.AutenticarUsuarioUseCase;
import br.com.bom.consultorio.usuarios.usecases.dtos.AutenticarUsuarioUseCaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        response.setNome(autenticarUsuarioUseCaseResponse.nome());

        return response;
    }
}
