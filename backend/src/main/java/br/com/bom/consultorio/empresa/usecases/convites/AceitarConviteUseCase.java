package br.com.bom.consultorio.empresa.usecases.convites;


import br.com.bom.consultorio.empresa.exceptions.ConviteExpiradoException;
import br.com.bom.consultorio.empresa.exceptions.ConviteNaoEncontradoException;
import br.com.bom.consultorio.empresa.exceptions.DestinatarioConviteIncorretoException;
import br.com.bom.consultorio.empresa.exceptions.UsuarioJaPercenteEmpresaException;
import br.com.bom.consultorio.empresa.models.convite.ConviteEmpresaModel;
import br.com.bom.consultorio.empresa.models.convite.StatusConviteEnum;
import br.com.bom.consultorio.empresa.models.empresa.UsuarioEmpresaModel;
import br.com.bom.consultorio.empresa.repositories.ConviteEmpresaRepository;
import br.com.bom.consultorio.usuarios.enums.PerfilAcessoUsuarioEmpresaEnum;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import br.com.bom.consultorio.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class AceitarConviteUseCase {

    public record Request(UUID identificador, UsuarioModel usuarioAceite) {
    }

    public record Response(String usuario, String empresa, PerfilAcessoUsuarioEmpresaEnum perfil) {
    }
    
    private final UsuarioRepository usuarioRepository;
    private final ConviteEmpresaRepository conviteEmpresaRepository;

    @Transactional
    public Response executar(Request aceitarConviteRequest) {
        ConviteEmpresaModel conviteEmpresaModel = this.conviteEmpresaRepository
                .findByCodigoAndStatus(aceitarConviteRequest.identificador().toString(), StatusConviteEnum.PENDENTE)
                .orElseThrow(ConviteNaoEncontradoException::new);

        if (conviteEmpresaModel.isExpirado()) {
            throw new ConviteExpiradoException();
        }

        String emailUsuarioAceite = StringUtils.defaultIfEmpty(
                aceitarConviteRequest.usuarioAceite().getEmail(),
                StringUtils.EMPTY
        );

        if (!emailUsuarioAceite.equalsIgnoreCase(conviteEmpresaModel.getEmail())) {
            throw new DestinatarioConviteIncorretoException();
        }

        UsuarioModel usuarioAceite = aceitarConviteRequest.usuarioAceite();

        if (usuarioAceite.possuiVinculoComEmpresa(conviteEmpresaModel.getEmpresa())) {
            throw new UsuarioJaPercenteEmpresaException();
        }

        log.info("Finalizando o convite {} - Usuário {} - Empresa {}",
                conviteEmpresaModel.getCodigo(), usuarioAceite.getIdentificador(), conviteEmpresaModel.getEmpresa().getIdentificador());

        this.concluirConvite(conviteEmpresaModel);
        this.adicionarUsuarioAceiteComoMembroEmpresa(conviteEmpresaModel, usuarioAceite);

        return new Response(usuarioAceite.getIdentificador(), conviteEmpresaModel.getEmpresa().getIdentificador(), conviteEmpresaModel.getPerfilAcesso());
    }

    private void concluirConvite(ConviteEmpresaModel conviteEmpresaModel) {
        conviteEmpresaModel.finalizar();
        this.conviteEmpresaRepository.save(conviteEmpresaModel);
    }

    private void adicionarUsuarioAceiteComoMembroEmpresa(ConviteEmpresaModel conviteEmpresaModel, UsuarioModel usuarioAceite) {
        usuarioAceite.adicionarVinculoEmpresa(conviteEmpresaModel.getEmpresa(), conviteEmpresaModel.getPerfilAcesso());
        this.usuarioRepository.save(usuarioAceite);
    }

}
