package br.com.bom.consultorio.usuarios.exceptions;

import br.com.bom.consultorio.shared.http.exceptions.AppException;
import br.com.bom.consultorio.usuarios.enums.UsuarioExceptionEnum;
import org.springframework.http.HttpStatus;

public class ConfirmacaoSenhaInvalidaException extends AppException {

    public ConfirmacaoSenhaInvalidaException() {
        super(HttpStatus.BAD_REQUEST, UsuarioExceptionEnum.CONFIRMACAO_SENHA_INVALIDA);
    }
}
