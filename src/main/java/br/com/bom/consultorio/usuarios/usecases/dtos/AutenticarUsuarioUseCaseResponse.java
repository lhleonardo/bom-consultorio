package br.com.bom.consultorio.usuarios.usecases.dtos;

public record AutenticarUsuarioUseCaseResponse(
        String token,
        String email,
        String nome,
        String identificadorUsuario
) {
}
