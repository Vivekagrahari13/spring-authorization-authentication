package com.spring.security.authentication.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {
    private String fullName;
    private String username;
    private String password;
}
