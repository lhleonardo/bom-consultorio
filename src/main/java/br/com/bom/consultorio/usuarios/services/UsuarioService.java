package br.com.bom.consultorio.usuarios.services;

import br.com.bom.consultorio.usuarios.exceptions.ConfirmacaoSenhaInvalidaException;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.payloads.requests.CriarUsuarioApiRequest;
import br.com.bom.consultorio.usuarios.usecases.BuscarUsuarioPeloEmailUseCase;
import br.com.bom.consultorio.usuarios.usecases.dtos.CriarUsuarioUseCaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final BuscarUsuarioPeloEmailUseCase buscarUsuarioPeloEmailUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.buscarUsuarioPeloEmailUseCase.executar(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public UsuarioModel criarNovoUsuario(CriarUsuarioApiRequest criarUsuarioApiRequest) {
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
