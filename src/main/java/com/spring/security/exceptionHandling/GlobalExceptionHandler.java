package com.spring.security.exceptionHandling;

import com.spring.security.generic.*;
import lombok.extern.log4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

import static com.spring.security.generic.Constants.UNEXPECTED_ERROR_OCCURED;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericErrorResponse> handleException(Exception ex) {
        log.error(UNEXPECTED_ERROR_OCCURED, ex);
        GenericErrorResponse errorResponse = GenericErrorResponse.builder()
                .success(false)
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(UNEXPECTED_ERROR_OCCURED)
                .data(Collections.emptyMap())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<GenericErrorResponse> handleGenericException(GenericException ex) {
        GenericErrorResponse errorResponse=GenericErrorResponse.builder()
                .success(false)
                .errorCode(ex.getStatusCode())
                .message(ex.getMessage())
                .data(Collections.emptyMap())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();

        GenericErrorResponse response = GenericErrorResponse.builder()
                .success(false)
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage)
                .data(Collections.emptyMap())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
