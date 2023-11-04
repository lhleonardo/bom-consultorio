package br.com.bom.consultorio.usuarios.usecases.dtos;

import lombok.Builder;

@Builder
public record CriarUsuarioUseCaseRequest(
    String email,
    String senha,
    boolean administrador
) {

    @Override
    public String toString() {
        return "CriarUsuarioDto{" +
                "email='" + email + '\'' +
                ", administrador=" + administrador +
                '}';
    }
}