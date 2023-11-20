package br.com.bom.consultorio.empresa.models.convite;

import br.com.bom.consultorio.empresa.models.EmpresaModel;
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
    private String codigoConvite;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private EmpresaModel empresa;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusConviteEnum status;

    @Column(name = "data_expiracao")
    private OffsetDateTime dataExpiracao;

    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private OffsetDateTime dataAtualizacao;
}
