package br.com.bom.consultorio.empresa.exceptions;

import br.com.bom.consultorio.empresa.enums.EmpresaExceptionEnum;
import br.com.bom.consultorio.shared.http.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class DuplicidadeConviteException extends AppException {
    public DuplicidadeConviteException() {
        super(HttpStatus.BAD_REQUEST, EmpresaExceptionEnum.DUPLICIDADE_CONVITE);
    }
}
