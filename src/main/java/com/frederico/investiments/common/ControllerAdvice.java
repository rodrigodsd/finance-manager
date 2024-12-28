package com.frederico.investiments.common;

import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.Instant;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    //TODO treat NoSuchElementException

    // TODO treat IllegalArgumentException

    // TODO treat UsernameNotFoundException

    // TODO treat MaxUploadSizeExceededException

    // @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ProblemDetail> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ProblemDetail.forStatusAndDetail(HttpStatus.EXPECTATION_FAILED, "File too large."));
    }

    @ExceptionHandler({DbActionExecutionException.class, SQLException.class})
    public ProblemDetail handleDbActionExecutionException(DbActionExecutionException exc) {
        return toProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, exc);
    }

    public ProblemDetail toProblemDetail(HttpStatus status, Exception ex) {
        var problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setDetail(ex.getCause().getMessage());
        problemDetail.setProperty("Module", "Plataforma");
        problemDetail.setProperty("TimeStamp", Instant.now());
        return problemDetail;
    }
}