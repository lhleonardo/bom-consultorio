package br.com.bom.consultorio.empresa.exceptions;

import br.com.bom.consultorio.empresa.enums.EmpresaExceptionEnum;
import br.com.bom.consultorio.shared.http.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class TrocarStatusConviteNaoPermitidoException extends AppException {

    private TrocarStatusConviteNaoPermitidoException(EmpresaExceptionEnum empresaExceptionEnum) {
        super(HttpStatus.PRECONDITION_FAILED, empresaExceptionEnum);
    }

    public static TrocarStatusConviteNaoPermitidoException cancelamentoNaoPermitido() {
        return new TrocarStatusConviteNaoPermitidoException(EmpresaExceptionEnum.RESTRICAO_CANCELAMENTO_CONVITE);
    }

    public static TrocarStatusConviteNaoPermitidoException expiracaoNaoPermitida() {
        return new TrocarStatusConviteNaoPermitidoException(EmpresaExceptionEnum.RESTRICAO_EXPIRACAO_CONVITE);
    }
}
