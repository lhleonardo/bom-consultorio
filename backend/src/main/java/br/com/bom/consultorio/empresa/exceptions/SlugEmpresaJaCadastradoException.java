package br.com.bom.consultorio.empresa.exceptions;

import br.com.bom.consultorio.empresa.enums.EmpresaExceptionEnum;
import br.com.bom.consultorio.shared.http.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class SlugEmpresaJaCadastradoException extends AppException {

    public SlugEmpresaJaCadastradoException() {
        super(HttpStatus.PRECONDITION_FAILED, EmpresaExceptionEnum.SLUG_EMPRESA_JA_CADASTRADO);
    }
}
