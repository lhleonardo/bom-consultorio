package br.com.bom.consultorio.empresa.models.empresa;

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

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "usuario_empresa")
public class UsuarioEmpresaModel {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private EmpresaModel empresa;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;

    @Column
    @Enumerated(EnumType.STRING)
    private PerfilAcessoUsuarioEmpresaEnum perfil;

    @Column(nullable = false)
    private OffsetDateTime dataCriacao;
}
