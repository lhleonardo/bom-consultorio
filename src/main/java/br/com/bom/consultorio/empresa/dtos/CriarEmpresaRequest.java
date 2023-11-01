package br.com.bom.consultorio.empresa.dtos;

import lombok.Data;

@Data
public class CriarEmpresaRequest {

    private String razaoSocial;

    private String nomeFantasia;

    private String cnpj;

    private String inscricaoEstadual;
}