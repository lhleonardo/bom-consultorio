package br.com.bom.consultorio.empresa.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/empresas")
@Tag(name = "Empresas", description = "Gerenciamento das informações das empresas na plataforma")
public class MembrosEmpresaController {

    @PostMapping("/convite")
    public void convidarMembroParaEmpresa() {}

    @DeleteMapping("/convite/{codigo}")
    public void cancelarConvite() {}

    @GetMapping("/convite")
    public void listaConvitesDaEmpresa() {}

}
