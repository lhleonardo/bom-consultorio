package br.com.bom.consultorio.usuarios.cli;

import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.repository.UsuarioRepository;
import br.com.bom.consultorio.usuarios.usecases.CriarNovoUsuarioUseCase;
import br.com.bom.consultorio.usuarios.usecases.dtos.CriarUsuarioDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CriarUsuarioMasterCli implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final CriarNovoUsuarioUseCase criarNovoUsuarioUseCase;

    @Override
    public void run(String... args) throws Exception {
        long quantidadeUsuariosCadastradas = this.usuarioRepository.count();

        if (quantidadeUsuariosCadastradas == 0) {
            CriarUsuarioDto payload = new CriarUsuarioDto("admin@bom.com.br", "bom-admin", true);

            UsuarioModel usuarioAdminCriado = this.criarNovoUsuarioUseCase.executar(payload);

            log.info("### Usuário master criado. Login: {}, senha: {}", payload.email(), payload.senha());
        }
    }
}
