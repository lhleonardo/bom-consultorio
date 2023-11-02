package br.com.bom.consultorio.empresa.enums;

import br.com.bom.consultorio.shared.http.handlers.exceptions.MensagensExceptionStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EmpresaExceptionEnum implements MensagensExceptionStrategy {
    EMPRESA_JA_CADASTRADA("EMP001", "Empresa já cadastrada para o CNPJ informado");

    private final String codigo;
    private final String mensagem;

    @Override
    public String getCodigo() {
        return this.codigo;
    }

    @Override
    public String getConteudo() {
        return this.mensagem;
    }
}
