package br.com.bom.consultorio.usuarios.models;

import br.com.bom.consultorio.empresa.models.EmpresaModel;
import br.com.bom.consultorio.usuarios.enums.PerfilAcessoUsuarioEmpresaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "usuario")
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    @UUID
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identificador;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "administrador", nullable = false)
    private boolean administradorPlataforma;

    @Column(name = "email_verificado", nullable = false)
    private boolean emailVerificado;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioEmpresaModel> empresasVinculadas;

    @Column
    private String nome;

    @Column
    private String telefone;

    @Column
    private String documento;


    @Column(name = "data_criacao", nullable = false)
    private OffsetDateTime dataCriacao;

    @Column(name = "data_alteracao", nullable = false)
    private OffsetDateTime dataAlteracao;

    public boolean possuiVinculoComEmpresa(EmpresaModel empresa) {
        if (Objects.isNull(empresa)) return false;

        return this.empresasVinculadas
                .stream()
                .map(UsuarioEmpresaModel::getEmpresa)
                .anyMatch(empresaVinculada -> empresaVinculada.getIdentificador().equals(empresa.getIdentificador()));
    }

    public PerfilAcessoUsuarioEmpresaEnum getPerfilAcessoParaEmpresa(EmpresaModel empresaModel) {
        if (this.isAdministradorPlataforma()) return PerfilAcessoUsuarioEmpresaEnum.ADMINISTRADOR;

        return this.getEmpresasVinculadas()
                .stream()
                .filter(vinculo -> vinculo.getEmpresa().getIdentificador().equals(empresaModel.getIdentificador()))
                .map(UsuarioEmpresaModel::getPerfil)
                .findFirst().orElseThrow();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }
}
