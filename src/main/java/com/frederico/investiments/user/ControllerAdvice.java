package com.frederico.investiments.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ControllerAdvice extends ResponseEntityExceptionHandler {

    //TODO treat NoSuchElementException

    // TODO treat IllegalArgumentException

    // TODO treat UsernameNotFoundException

    // TODO treat MaxUploadSizeExceededException

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ProblemDetail> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ProblemDetail.forStatusAndDetail(HttpStatus.EXPECTATION_FAILED,"File too large."));
    }
}