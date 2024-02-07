package br.com.bom.consultorio.empresa.exceptions;

import br.com.bom.consultorio.empresa.enums.EmpresaExceptionEnum;
import br.com.bom.consultorio.shared.http.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class IdentificadorEmpresaNaoInformadoException extends AppException {
    public IdentificadorEmpresaNaoInformadoException() {
        super(HttpStatus.BAD_REQUEST, EmpresaExceptionEnum.IDENTIFICADOR_EMPRESA_NAO_INFORMADO);
    }
}
