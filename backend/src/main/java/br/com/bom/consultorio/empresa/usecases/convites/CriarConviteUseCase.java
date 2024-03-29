package br.com.bom.consultorio.empresa.usecases.convites;

import br.com.bom.consultorio.empresa.exceptions.ConviteSemEmpresaException;
import br.com.bom.consultorio.empresa.exceptions.DuplicidadeConviteException;
import br.com.bom.consultorio.empresa.exceptions.UsuarioJaPercenteEmpresaException;
import br.com.bom.consultorio.empresa.models.convite.ConviteEmpresaModel;
import br.com.bom.consultorio.empresa.models.convite.StatusConviteEnum;
import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.empresa.repositories.ConviteEmpresaRepository;
import br.com.bom.consultorio.empresa.repositories.EmpresaRepository;
import br.com.bom.consultorio.shared.auth.services.UsuarioAutenticadoService;
import br.com.bom.consultorio.usuarios.enums.PerfilAcessoUsuarioEmpresaEnum;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class CriarConviteUseCase {

    /**
     * Dados necessários para criar um convite de membro em uma determinada empresa.
     *
     * @param empresaModel empresa que o convite será vinculado
     * @param perfilAcesso perfil de acesso que o usuário terá na empresa ao ingressar pelo convite
     * @param email        e-mail do usuário que receberá o convite
     */
    public record Request
            (EmpresaModel empresaModel, PerfilAcessoUsuarioEmpresaEnum perfilAcesso, @Email String email) {

    }

    private final EmpresaRepository empresaRepository;
    private final ConviteEmpresaRepository conviteEmpresaRepository;
    private final UsuarioAutenticadoService usuarioAutenticadoService;

    /**
     * Cria um novo convite para convidar um usuário futuro a ingressar como membro da empresa.
     *
     * @param criarConviteUseCaseRequest dados necessários para criar um novo convite.
     * @throws ConviteSemEmpresaException        caso não informe a empresa do convite
     * @throws UsuarioJaPercenteEmpresaException caso já exista um membro para a empresa com o e-mail do convite
     */
    @Transactional
    public void executar(Request criarConviteUseCaseRequest) {
        EmpresaModel empresaModel = criarConviteUseCaseRequest.empresaModel();
        if (Objects.isNull(empresaModel)) {
            throw new ConviteSemEmpresaException();
        }

        String email = criarConviteUseCaseRequest.email();

        if (this.empresaRepository.existsMembroByEmail(empresaModel.getId(), email)) {
            throw new UsuarioJaPercenteEmpresaException();
        }

        if (this.existeConviteAtivoCadastrado(criarConviteUseCaseRequest)) {
            throw new DuplicidadeConviteException();
        }

        String codigoConvite = UUID.randomUUID().toString();

        ConviteEmpresaModel conviteEmpresaModel = new ConviteEmpresaModel();
        conviteEmpresaModel.setCodigo(codigoConvite);
        conviteEmpresaModel.setStatus(StatusConviteEnum.PENDENTE);
        conviteEmpresaModel.setCriadoPor(this.usuarioAutenticadoService.getUsuarioAutenticado());

        conviteEmpresaModel.setDataCriacao(OffsetDateTime.now());
        conviteEmpresaModel.setDataExpiracao(this.calcularDataExpiracao());

        conviteEmpresaModel.setEmail(criarConviteUseCaseRequest.email());
        conviteEmpresaModel.setEmpresa(criarConviteUseCaseRequest.empresaModel());
        conviteEmpresaModel.setPerfilAcesso(criarConviteUseCaseRequest.perfilAcesso());

        this.conviteEmpresaRepository.save(conviteEmpresaModel);
    }

    /**
     * Configura a data de expiração para daqui dois dias, com horário máximo ao fim do dia.
     *
     * @return OffsetDateTime com D + 2 (fim do dia).
     */
    private OffsetDateTime calcularDataExpiracao() {
        return OffsetDateTime.now().plusDays(2).with(LocalTime.MAX);
    }

    private boolean existeConviteAtivoCadastrado(Request criarConviteUseCaseRequest) {
        return this.conviteEmpresaRepository
                .findByEmailAndStatusAndEmpresa(criarConviteUseCaseRequest.email(), StatusConviteEnum.PENDENTE, criarConviteUseCaseRequest.empresaModel())
                .isPresent();
    }
}
