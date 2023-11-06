package br.com.bom.consultorio.usuarios.usecases;

import br.com.bom.consultorio.empresa.models.EmpresaModel;
import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import br.com.bom.consultorio.shared.jwt.services.JwtService;
import br.com.bom.consultorio.usuarios.models.UsuarioEmpresaModel;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.usecases.dtos.AutenticarUsuarioUseCaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

        boolean adminPlataforma = usuarioModel.isAdministradorPlataforma();
        boolean possuiVinculoComEmpresa = usuarioModel.possuiVinculoComEmpresa(EmpresaTenantContext.getEmpresaAtual());

        if (!adminPlataforma && !possuiVinculoComEmpresa) {
            throw new BadCredentialsException("Bad credentials");
        }

        String token = this.jwtService.gerarTokenJwt(usuarioModel);

        return new AutenticarUsuarioUseCaseResponse(token, usuarioModel.getEmail(), usuarioModel.getIdentificador());
    }
}
