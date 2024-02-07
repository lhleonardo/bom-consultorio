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
public class CustomUserDetailsService implements UserDetailsService {

    private final BuscarUsuarioPeloEmailUseCase buscarUsuarioPeloEmailUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.buscarUsuarioPeloEmailUseCase.executar(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }


}
