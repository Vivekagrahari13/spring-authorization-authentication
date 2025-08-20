package com.spring.security.authentication.controller;

import com.spring.security.authentication.dtos.*;
import com.spring.security.authentication.service.*;
import com.spring.security.generic.*;
import jakarta.validation.*;
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
    public ResponseEntity<GenericSuccessResponse> registerUser(@RequestBody UserRegisterRequest registerRequest){
        GenericSuccessResponse registerResponse=userService.registerUser(registerRequest);
        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericSuccessResponse> login(@Valid @RequestBody UserRegisterRequest loginRequest){
        GenericSuccessResponse loginResponse=userService.loginUser(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/verify-token")
    public ResponseEntity<GenericSuccessResponse> verifyToken(@RequestParam String accessToken){
        return ResponseEntity.ok(userService.verifyToken(accessToken));
    }
}
