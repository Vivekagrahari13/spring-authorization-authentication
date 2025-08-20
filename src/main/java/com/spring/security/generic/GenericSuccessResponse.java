package com.spring.security.generic;

import lombok.*;

@Builder
@Data
public class GenericSuccessResponse {
    private Boolean success;
    private String message;
    private Integer statusCode;
    private Object data;
}
