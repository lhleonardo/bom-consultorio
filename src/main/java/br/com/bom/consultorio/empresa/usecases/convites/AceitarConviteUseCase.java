package br.com.bom.consultorio.empresa.usecases.convites;


import br.com.bom.consultorio.empresa.exceptions.ConviteExpiradoException;
import br.com.bom.consultorio.empresa.exceptions.ConviteNaoEncontradoException;
import br.com.bom.consultorio.empresa.models.convite.ConviteEmpresaModel;
import br.com.bom.consultorio.empresa.models.convite.StatusConviteEnum;
import br.com.bom.consultorio.empresa.repositories.ConviteEmpresaRepository;
import br.com.bom.consultorio.usuarios.usecases.dtos.CriarUsuarioUseCaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Component
@RequiredArgsConstructor
public class AceitarConviteUseCase {

    public record Request(String identificador, CriarUsuarioUseCaseRequest criarUsuarioUseCaseRequest) {
    }

    public record Response() {
    }
    
    private final ConviteEmpresaRepository conviteEmpresaRepository;

    @Transactional
    public void executar(Request aceitarConviteRequest) {
        ConviteEmpresaModel conviteEmpresaModel = this.conviteEmpresaRepository
                .findByCodigoAndStatus(aceitarConviteRequest.identificador(), StatusConviteEnum.PENDENTE)
                .orElseThrow(ConviteNaoEncontradoException::new);

        if (conviteEmpresaModel.isExpirado()) {
            throw new ConviteExpiradoException();
        }





    }

}
