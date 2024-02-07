package br.com.bom.consultorio.empresa.exceptions;

import br.com.bom.consultorio.empresa.enums.EmpresaExceptionEnum;
import br.com.bom.consultorio.shared.http.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class DestinatarioConviteIncorretoException extends AppException {

    public DestinatarioConviteIncorretoException() {
        super(HttpStatus.PRECONDITION_FAILED, EmpresaExceptionEnum.RESTRICAO_CONVITE_DESTINATARIO_DIFERENTE);
    }
}
