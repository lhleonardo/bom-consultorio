package br.com.bom.consultorio.usuarios.enums;

import br.com.bom.consultorio.shared.http.handlers.exceptions.MensagensExceptionStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UsuarioExceptionEnum implements MensagensExceptionStrategy {
    EMAIL_USUARIO_JA_CADASTRADO("USU001", "O e-mail do usuário já encontra-se em uso"),
    CONFIRMACAO_SENHA_INVALIDA("USU002", "A senha e a confirmação informada não coincidem");

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
