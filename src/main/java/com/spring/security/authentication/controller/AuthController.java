package com.spring.security.authentication.controller;

import com.spring.security.authentication.dtos.*;
import com.spring.security.authentication.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    //signup
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody UserRegisterRequest registerRequest){
        UserRegisterResponse registerResponse=userService.registerUser(registerRequest);
        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<UserRegisterResponse> login(@RequestBody UserRegisterRequest loginRequest){
        UserRegisterResponse loginResponse=userService.loginUser(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
