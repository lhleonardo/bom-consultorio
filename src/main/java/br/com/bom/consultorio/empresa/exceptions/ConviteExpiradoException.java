package br.com.bom.consultorio.empresa.exceptions;

import br.com.bom.consultorio.empresa.enums.EmpresaExceptionEnum;
import br.com.bom.consultorio.shared.http.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ConviteExpiradoException extends AppException {

    public ConviteExpiradoException() {
        super(HttpStatus.PRECONDITION_FAILED, EmpresaExceptionEnum.CONVITE_EXPIRADO);
    }
}
