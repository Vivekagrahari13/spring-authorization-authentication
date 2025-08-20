package com.spring.security.generic;

import lombok.*;

import java.time.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericErrorResponse {
    private Integer errorCode;
    private String message;
    private Boolean success;
    private Object data;
    private LocalDateTime timestamp;
}
