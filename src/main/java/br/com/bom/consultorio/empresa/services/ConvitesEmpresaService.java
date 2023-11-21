package br.com.bom.consultorio.empresa.services;

import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.empresa.payloads.requests.CriarNovoConviteApiRequest;
import br.com.bom.consultorio.empresa.usecases.convites.CriarConviteUseCase;
import br.com.bom.consultorio.empresa.usecases.convites.dtos.CriarConviteUseCaseRequest;
import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvitesEmpresaService {

    private final CriarConviteUseCase criarConviteUseCase;

    public void convidar(CriarNovoConviteApiRequest criarNovoConviteApiRequest) {
        EmpresaModel empresaModel = EmpresaTenantContext.getEmpresaAtual();

        CriarConviteUseCaseRequest request = new CriarConviteUseCaseRequest(
                empresaModel,
                criarNovoConviteApiRequest.getPerfilAcesso(),
                criarNovoConviteApiRequest.getEmail()
        );

        this.criarConviteUseCase.executar(request);
    }
}
