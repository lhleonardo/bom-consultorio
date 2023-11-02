package br.com.bom.consultorio.usuarios.models;

import br.com.bom.consultorio.empresa.models.EmpresaModel;
import br.com.bom.consultorio.usuarios.enums.PerfilEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.UUID;

import java.time.OffsetDateTime;

@Entity(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue
    private Integer id;

    @UUID
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identificador;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(name = "password_hashed", nullable = false)
    private String passwordHashed;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilEnum perfil;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private EmpresaModel empresa;

    @Column(name = "data_criacao", nullable = false)
    private OffsetDateTime dataCriacao;

    @Column(name = "data_alteracao", nullable = false)
    private OffsetDateTime dataAlteracao;
}
