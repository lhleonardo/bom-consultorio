package br.com.bom.consultorio.usuarios.controllers;

import br.com.bom.consultorio.usuarios.payloads.requests.LoginApiRequest;
import br.com.bom.consultorio.usuarios.payloads.responses.LoginApiResponse;
import br.com.bom.consultorio.usuarios.services.AutenticacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public LoginApiResponse login(@RequestBody @Valid LoginApiRequest loginApiRequest) {
        return this.autenticacaoService.login(loginApiRequest);
    }
}
