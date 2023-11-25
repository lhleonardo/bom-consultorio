package br.com.bom.consultorio.empresa.models.convite;

import br.com.bom.consultorio.empresa.exceptions.TrocarStatusConviteNaoPermitidoException;
import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.usuarios.enums.PerfilAcessoUsuarioEmpresaEnum;
import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.time.OffsetDateTime;

@Entity
@Table(name = "empresa_convite")
@Getter
@Setter
public class ConviteEmpresaModel {

    @Id
    @GeneratedValue
    private Long id;

    @UUID
    @Column(name = "codigo_convite")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private EmpresaModel empresa;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil_acesso_convite")
    private PerfilAcessoUsuarioEmpresaEnum perfilAcesso;

    @ManyToOne
    @JoinColumn(name = "id_usuario_criador")
    private UsuarioModel criadoPor;

    private String email;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusConviteEnum status;

    @Column(name = "data_expiracao")
    private OffsetDateTime dataExpiracao;

    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private OffsetDateTime dataAtualizacao;

    public void expirar() {
        if (!this.getStatus().equals(StatusConviteEnum.PENDENTE)) {
            throw TrocarStatusConviteNaoPermitidoException.expiracaoNaoPermitida();
        }
        this.trocarStatus(StatusConviteEnum.EXPIRADO);
    }

    public void cancelar() {
        if (!this.getStatus().equals(StatusConviteEnum.PENDENTE)) {
            throw TrocarStatusConviteNaoPermitidoException.cancelamentoNaoPermitido();
        }
        this.trocarStatus(StatusConviteEnum.CANCELADO);
    }

    private void trocarStatus(StatusConviteEnum status) {
        this.status = status;
        this.dataAtualizacao = OffsetDateTime.now();
    }
}
