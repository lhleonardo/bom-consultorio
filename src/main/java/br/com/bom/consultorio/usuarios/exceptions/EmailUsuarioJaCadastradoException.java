package br.com.bom.consultorio.usuarios.exceptions;

import br.com.bom.consultorio.shared.http.exceptions.AppException;
import br.com.bom.consultorio.usuarios.enums.UsuarioExceptionEnum;
import org.springframework.http.HttpStatus;

public class EmailUsuarioJaCadastradoException extends AppException {

    public EmailUsuarioJaCadastradoException() {
        super(HttpStatus.PRECONDITION_FAILED, UsuarioExceptionEnum.EMAIL_USUARIO_JA_CADASTRADO);
    }
}
