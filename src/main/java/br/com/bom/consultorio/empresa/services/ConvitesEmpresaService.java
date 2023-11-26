package br.com.bom.consultorio.empresa.services;

import br.com.bom.consultorio.empresa.exceptions.IdentificadorEmpresaNaoInformadoException;
import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.empresa.payloads.requests.CriarNovoConviteApiRequest;
import br.com.bom.consultorio.empresa.payloads.responses.AceiteConviteApiResponse;
import br.com.bom.consultorio.empresa.usecases.convites.AceitarConviteUseCase;
import br.com.bom.consultorio.empresa.usecases.convites.CancelarConviteUseCase;
import br.com.bom.consultorio.empresa.usecases.convites.CriarConviteUseCase;
import br.com.bom.consultorio.empresa.usecases.convites.DesativarConvitesExpiradosUseCase;
import br.com.bom.consultorio.shared.auth.services.UsuarioAutenticadoService;
import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class ConvitesEmpresaService {

    private final UsuarioAutenticadoService usuarioAutenticadoService;

    private final CriarConviteUseCase criarConviteUseCase;
    private final DesativarConvitesExpiradosUseCase desativarConvitesExpiradosUseCase;
    private final CancelarConviteUseCase cancelarConviteUseCase;
    private final AceitarConviteUseCase aceitarConviteUseCase;

    public void convidar(CriarNovoConviteApiRequest criarNovoConviteApiRequest) {
        EmpresaModel empresaModel = EmpresaTenantContext.getEmpresaAtual();

        CriarConviteUseCase.Request request = new CriarConviteUseCase.Request(
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

    public AceiteConviteApiResponse aceitar(UUID codigoConvite) {
        UsuarioModel usuarioAutenticadoAceite = this.usuarioAutenticadoService.getUsuarioAutenticado();
        AceitarConviteUseCase.Request request = new AceitarConviteUseCase.Request(codigoConvite, usuarioAutenticadoAceite);

        AceitarConviteUseCase.Response response = this.aceitarConviteUseCase.executar(request);

        log.info("Dados do aceite: {}", response);

        AceiteConviteApiResponse aceiteConviteApiResponse = new AceiteConviteApiResponse();
        aceiteConviteApiResponse.setEmpresa(response.empresa());
        aceiteConviteApiResponse.setUsuario(response.usuario());
        aceiteConviteApiResponse.setPerfil(response.perfil());

        return aceiteConviteApiResponse;
    }
}
