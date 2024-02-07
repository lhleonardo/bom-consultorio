package br.com.bom.consultorio.empresa.usecases.convites;

import br.com.bom.consultorio.empresa.exceptions.ConviteNaoEncontradoException;
import br.com.bom.consultorio.empresa.models.convite.ConviteEmpresaModel;
import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.empresa.repositories.ConviteEmpresaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class CancelarConviteUseCase {

    private final ConviteEmpresaRepository conviteEmpresaRepository;

    public record Request(UUID codigo, EmpresaModel empresaModel) {
    }

    @Transactional
    public void executar(Request request) {
        ConviteEmpresaModel conviteEmpresaModel = this.conviteEmpresaRepository
                .findByCodigoAndEmpresa(request.codigo().toString(), request.empresaModel())
                .orElseThrow(ConviteNaoEncontradoException::new);

        conviteEmpresaModel.cancelar();

        this.conviteEmpresaRepository.save(conviteEmpresaModel);
    }
}
