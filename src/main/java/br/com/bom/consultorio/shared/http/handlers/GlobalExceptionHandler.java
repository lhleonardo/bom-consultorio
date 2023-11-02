package br.com.bom.consultorio.shared.http.handlers;

import br.com.bom.consultorio.shared.http.exceptions.AppException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {

        Map<String, String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(key -> key.getField(), value -> value.getDefaultMessage()));

        ProblemDetail problemDetail = ex.getBody();
        problemDetail.setProperty("validation-errors", errorMessages);

        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ExceptionHandler(AppException.class)
    ResponseEntity<ProblemDetail> handleNumeroNaoInformadoException(AppException appException, HttpServletRequest httpServletRequest) {
        ProblemDetail problemDetail = appException.toProblemDetail();
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("http_method", httpServletRequest.getMethod());

        // TODO: Trocar para o id da requisição do sleuth (que não existe no spring 3.x)
        problemDetail.setProperty("request_id", UUID.randomUUID().toString());

        return ResponseEntity.status(appException.getHttpStatus()).body(problemDetail);
    }
}
