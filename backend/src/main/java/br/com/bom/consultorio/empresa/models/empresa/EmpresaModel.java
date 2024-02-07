package br.com.bom.consultorio.empresa.models.empresa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "empresa")
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

    @OneToMany(mappedBy = "empresa")
    private List<UsuarioEmpresaModel> membros;

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
