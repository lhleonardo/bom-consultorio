package br.com.bom.consultorio.empresa.exceptions;

import br.com.bom.consultorio.empresa.enums.EmpresaExceptionEnum;
import br.com.bom.consultorio.shared.http.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class IdentificadorEmpresaInvalidoException extends AppException {

    public IdentificadorEmpresaInvalidoException() {
        super(HttpStatus.BAD_REQUEST, EmpresaExceptionEnum.IDENTIFICADOR_EMPRESA_INVALIDO);
    }
}
