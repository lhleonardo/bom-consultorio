package br.com.bom.consultorio.empresa.exceptions;

import br.com.bom.consultorio.empresa.enums.EmpresaExceptionEnum;
import br.com.bom.consultorio.empresa.repositories.ConviteEmpresaRepository;
import br.com.bom.consultorio.shared.http.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class ConviteSemEmpresaException extends AppException {

    public ConviteSemEmpresaException() {
        super(HttpStatus.PRECONDITION_FAILED, EmpresaExceptionEnum.CONVITE_EMPRESA_INVALIDA);
    }
}
