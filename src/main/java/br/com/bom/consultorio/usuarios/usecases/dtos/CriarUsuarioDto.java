package br.com.bom.consultorio.usuarios.usecases.dtos;

import lombok.Builder;

@Builder
public record CriarUsuarioDto (
    String email,
    String senha,
    boolean administrador
) {

    @Override
    public String toString() {
        return "CriarUsuarioDto{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", administrador=" + administrador +
                '}';
    }
}