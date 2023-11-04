package br.com.bom.consultorio.shared.http.exceptions;

import br.com.bom.consultorio.shared.http.handlers.exceptions.MensagensExceptionStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public abstract class AppException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final MensagensExceptionStrategy mensagem;

    protected AppException(HttpStatus httpStatus, MensagensExceptionStrategy mensagem, Object... args) {
        super(mensagem.formatar(args));

        this.httpStatus = httpStatus;
        this.mensagem = mensagem;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(this.getHttpStatus(), this.getLocalizedMessage());
        problemDetail.setTitle(mensagem.getCodigo());
        problemDetail.setDetail(this.getMessage());
        return problemDetail;
    }
}
