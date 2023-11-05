package br.com.bom.consultorio.usuarios.models;

import br.com.bom.consultorio.empresa.models.EmpresaModel;
import br.com.bom.consultorio.usuarios.enums.PerfilEnum;
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

import java.time.OffsetDateTime;

@Entity
@Getter
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
    private PerfilEnum perfil;

    @Column(nullable = false)
    private OffsetDateTime dataCriacao;
}
