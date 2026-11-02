package com.gameflix.controller;

import com.gameflix.dto.auth.AuthResponse;
import com.gameflix.dto.auth.LoginRequest;
import com.gameflix.dto.auth.RegisterRequest;
import com.gameflix.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
        String token = authService.registerUser(request);
        AuthResponse response = AuthResponse.from(token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        String token = authService.loginUser(request);
        AuthResponse response = AuthResponse.from(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}