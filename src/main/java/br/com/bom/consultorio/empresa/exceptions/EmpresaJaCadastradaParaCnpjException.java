package br.com.bom.consultorio.empresa.exceptions;

import br.com.bom.consultorio.empresa.enums.EmpresaExceptionEnum;
import br.com.bom.consultorio.shared.http.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class EmpresaJaCadastradaParaCnpjException extends AppException {

    public EmpresaJaCadastradaParaCnpjException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, EmpresaExceptionEnum.EMPRESA_JA_CADASTRADA);
    }
}
