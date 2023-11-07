package br.com.bom.consultorio.usuarios.payloads.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginApiRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
