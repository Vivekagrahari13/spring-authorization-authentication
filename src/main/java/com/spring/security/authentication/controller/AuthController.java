package com.spring.security.authentication.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Home Page!";
    }
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome to Admin Dashboard!";
    }
    @GetMapping("/admin/home")
    public String adminHome() {
        return "Welcome to Admin Home!";
    }
    @GetMapping("/user/profile")
    public String userProfile() {
        return "Welcome to User Profile!";
    }
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "Access-denied!!";
    }




}
