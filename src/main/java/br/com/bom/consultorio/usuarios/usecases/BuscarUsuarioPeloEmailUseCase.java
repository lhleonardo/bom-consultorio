package br.com.bom.consultorio.usuarios.usecases;

import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.repository.UsuarioRepository;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BuscarUsuarioPeloEmailUseCase {

    private final UsuarioRepository usuarioRepository;

    public Optional<UsuarioModel> executar(@Email String email) {
        return this.usuarioRepository.findByEmailAndAtivoIsTrue(email);
    }
}
