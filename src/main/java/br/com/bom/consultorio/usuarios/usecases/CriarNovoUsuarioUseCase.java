package br.com.bom.consultorio.usuarios.usecases;

import br.com.bom.consultorio.usuarios.exceptions.EmailUsuarioJaCadastradoException;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.repository.UsuarioRepository;
import br.com.bom.consultorio.usuarios.usecases.dtos.CriarUsuarioUseCaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class CriarNovoUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * Cria um novo usuário capaz de acessar a plataforma. Poderá exercer o papel de administrador da plataforma
     * ou pertencer a empresas específicas.
     * <p>
     * Se o usuário for um administrador, então não será realizado o vínculo do usuário com alguma empresa, já que ele
     * possuirá acesso a todas as empresas para manutenção e controle.
     *
     * @param criarUsuarioUseCaseRequest dados necessários para criar um usuário na plataforma
     * @return
     */
    public UsuarioModel executar(CriarUsuarioUseCaseRequest criarUsuarioUseCaseRequest) {
        log.info("Criando usuário {}", criarUsuarioUseCaseRequest);

        if (this.usuarioRepository.existsByEmail(criarUsuarioUseCaseRequest.email())) {
            throw new EmailUsuarioJaCadastradoException();
        }

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setEmail(criarUsuarioUseCaseRequest.email());
        usuarioModel.setIdentificador(UUID.randomUUID().toString());
        usuarioModel.setDataCriacao(OffsetDateTime.now());

        usuarioModel.setSenha(this.passwordEncoder.encode(criarUsuarioUseCaseRequest.senha()));

        usuarioModel.setAtivo(Boolean.TRUE);
        usuarioModel.setEmailVerificado(Boolean.FALSE);
        usuarioModel.setAdministradorPlataforma(criarUsuarioUseCaseRequest.administrador());

        // dados opcionais
        usuarioModel.setNome(criarUsuarioUseCaseRequest.nome());
        usuarioModel.setDocumento(criarUsuarioUseCaseRequest.documento());
        usuarioModel.setTelefone(criarUsuarioUseCaseRequest.telefone());

        return this.usuarioRepository.save(usuarioModel);
    }

}
