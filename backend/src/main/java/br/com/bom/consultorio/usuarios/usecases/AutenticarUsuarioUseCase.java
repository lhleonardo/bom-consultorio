package br.com.bom.consultorio.usuarios.usecases;

import br.com.bom.consultorio.shared.jwt.services.JwtService;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.usecases.dtos.AutenticarUsuarioUseCaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutenticarUsuarioUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AutenticarUsuarioUseCaseResponse executar(String email, String senha) {
        UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken(email, senha);

        Authentication authentication = this.authenticationManager.authenticate(request);
        UsuarioModel usuarioModel = (UsuarioModel) authentication.getPrincipal();

        String token = this.jwtService.gerarTokenJwt(usuarioModel);

        return new AutenticarUsuarioUseCaseResponse(token, usuarioModel.getEmail(), usuarioModel.getNome(), usuarioModel.getIdentificador());
    }
}
