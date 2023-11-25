package br.com.bom.consultorio.empresa.services;

import br.com.bom.consultorio.empresa.exceptions.IdentificadorEmpresaNaoInformadoException;
import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.empresa.payloads.requests.CriarNovoConviteApiRequest;
import br.com.bom.consultorio.empresa.usecases.convites.CancelarConviteUseCase;
import br.com.bom.consultorio.empresa.usecases.convites.CriarConviteUseCase;
import br.com.bom.consultorio.empresa.usecases.convites.DesativarConvitesExpiradosUseCase;
import br.com.bom.consultorio.empresa.usecases.convites.dtos.CriarConviteUseCaseRequest;
import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class ConvitesEmpresaService {

    private final CriarConviteUseCase criarConviteUseCase;
    private final DesativarConvitesExpiradosUseCase desativarConvitesExpiradosUseCase;
    private final CancelarConviteUseCase cancelarConviteUseCase;

    public void convidar(CriarNovoConviteApiRequest criarNovoConviteApiRequest) {
        EmpresaModel empresaModel = EmpresaTenantContext.getEmpresaAtual();

        CriarConviteUseCaseRequest request = new CriarConviteUseCaseRequest(
                empresaModel,
                criarNovoConviteApiRequest.getPerfilAcesso(),
                criarNovoConviteApiRequest.getEmail()
        );

        this.criarConviteUseCase.executar(request);
    }

    public void desativarConvitesExpirados() {
        log.info("Disparando processo de desativar convites expirados");
        this.desativarConvitesExpiradosUseCase.executar();
    }

    public void cancelar(UUID codigoConvite) {
        if (!EmpresaTenantContext.possuiValor()) {
            throw new IdentificadorEmpresaNaoInformadoException();
        }

        CancelarConviteUseCase.Request request = new CancelarConviteUseCase
                .Request(codigoConvite, EmpresaTenantContext.getEmpresaAtual());

        this.cancelarConviteUseCase.executar(request);
    }
}
