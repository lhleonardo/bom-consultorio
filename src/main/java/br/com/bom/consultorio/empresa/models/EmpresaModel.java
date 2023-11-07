package br.com.bom.consultorio.empresa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity(name = "empresa")
public class EmpresaModel {

    @Id
    @GeneratedValue
    private Integer id;

    @UUID
    @Column(nullable = false)
    private String identificador;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;

    @URL
    @Column
    private String site;

    @Column
    private String logotipo;

    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    @Column(name = "data_alteracao")
    private OffsetDateTime dataAlteracao;
}
