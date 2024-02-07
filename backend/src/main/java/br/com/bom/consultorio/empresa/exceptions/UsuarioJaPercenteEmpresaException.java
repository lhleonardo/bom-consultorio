package br.com.bom.consultorio.empresa.exceptions;

import br.com.bom.consultorio.empresa.enums.EmpresaExceptionEnum;
import br.com.bom.consultorio.shared.http.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class UsuarioJaPercenteEmpresaException extends AppException {

    public UsuarioJaPercenteEmpresaException() {
        super(HttpStatus.PRECONDITION_FAILED, EmpresaExceptionEnum.USUARIO_JA_EH_MEMBRO_EMPRESA);
    }

}
