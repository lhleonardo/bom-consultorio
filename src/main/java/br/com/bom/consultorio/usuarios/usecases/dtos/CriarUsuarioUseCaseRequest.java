package br.com.bom.consultorio.usuarios.usecases.dtos;

import br.com.bom.consultorio.usuarios.payloads.requests.CriarUsuarioApiRequest;
import lombok.Builder;

@Builder
public record CriarUsuarioUseCaseRequest(
    String email,
    String senha,
    boolean administrador,
    String nome,
    String documento,
    String telefone
) {

    @Override
    public String toString() {
        return "CriarUsuarioDto{" +
                "email='" + email + '\'' +
                ", administrador=" + administrador +
                '}';
    }

    public static CriarUsuarioUseCaseRequest fromApiRequest(CriarUsuarioApiRequest criarUsuarioApiRequest, boolean administrador) {
        return CriarUsuarioUseCaseRequest.builder()
                .email(criarUsuarioApiRequest.getEmail())
                .senha(criarUsuarioApiRequest.getSenha())
                .administrador(administrador)
                .nome(criarUsuarioApiRequest.getNome())
                .documento(criarUsuarioApiRequest.getDocumento())
                .telefone(criarUsuarioApiRequest.getTelefone())
                .build();
    }
}