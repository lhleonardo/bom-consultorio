package br.com.bom.consultorio.shared.http.handlers.exceptions;

import java.text.MessageFormat;

public interface MensagensExceptionStrategy {

    String getCodigo();
    String getConteudo();

    default String formatar(Object... args) {
        return MessageFormat.format(this.getConteudo(), args);
    }
}
