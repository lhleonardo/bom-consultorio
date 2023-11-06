package br.com.bom.consultorio.usuarios.usecases;

import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BuscarUsuarioPeloIdentificadorUseCase {

    private final UsuarioRepository usuarioRepository;

    public Optional<UsuarioModel> executar(String identificador) {
        if (!this.validarUUIDInformado(identificador)) return Optional.empty();

        return this.usuarioRepository.findByIdentificador(identificador);
    }

    private boolean validarUUIDInformado(String uuid) {
        try {
            UUID.fromString(uuid).toString();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
