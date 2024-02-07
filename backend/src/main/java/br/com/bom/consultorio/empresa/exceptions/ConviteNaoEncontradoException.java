package br.com.bom.consultorio.empresa.exceptions;

import br.com.bom.consultorio.empresa.enums.EmpresaExceptionEnum;
import br.com.bom.consultorio.shared.http.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ConviteNaoEncontradoException extends AppException {

    public ConviteNaoEncontradoException() {
        super(HttpStatus.BAD_REQUEST, EmpresaExceptionEnum.CONVITE_NAO_ENCONTRADO);
    }
}
