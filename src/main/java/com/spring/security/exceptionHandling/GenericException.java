package com.spring.security.exceptionHandling;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class GenericException extends RuntimeException {
    private final String errorMessage;
    private final int statusCode;

    /**
     * Constructs a new GenericException with the specified status code and error message.
     *
     * @param statusCode   the HTTP status code associated with the exception
     * @param errorMessage the error message describing the exception
     */
    public GenericException(int statusCode, String errorMessage) {
        super(errorMessage);
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
}
