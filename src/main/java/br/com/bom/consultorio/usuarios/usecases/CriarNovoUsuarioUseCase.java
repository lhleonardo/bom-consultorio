package br.com.bom.consultorio.usuarios.usecases;

import br.com.bom.consultorio.usuarios.exceptions.EmailUsuarioJaCadastradoException;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.repository.UsuarioRepository;
import br.com.bom.consultorio.usuarios.usecases.dtos.CriarUsuarioDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class CriarNovoUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public UsuarioModel executar(CriarUsuarioDto criarUsuarioDto) {
        log.info("Criando usuário {}", criarUsuarioDto);

        if (this.usuarioRepository.existsByEmail(criarUsuarioDto.email())) {
            throw new EmailUsuarioJaCadastradoException();
        }

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setEmail(criarUsuarioDto.email());
        usuarioModel.setIdentificador(UUID.randomUUID().toString());
        usuarioModel.setDataCriacao(OffsetDateTime.now());
        usuarioModel.setAdministradorGlobal(false);

        // TODO: realizar o hash da senha
        usuarioModel.setSenhaHash(criarUsuarioDto.senha());

        return this.usuarioRepository.save(usuarioModel);
    }

}
