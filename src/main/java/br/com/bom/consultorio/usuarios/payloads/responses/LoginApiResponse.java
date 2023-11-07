package br.com.bom.consultorio.usuarios.payloads.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginApiResponse {

    private String email;
    private LocalDateTime dataAutenticacao;
    private String token;

    private String identificadorUsuario;
    private String empresaAutenticada;
}
