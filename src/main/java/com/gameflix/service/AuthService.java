package com.gameflix.service;

import com.gameflix.dto.user.CreateUserCommand;
import com.gameflix.dto.auth.LoginRequest;
import com.gameflix.dto.auth.RegisterRequest;
import com.gameflix.exception.auth.InvalidCredentialsException;
import com.gameflix.model.User;
import com.gameflix.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(RegisterRequest request) {
        String passwordHash = passwordEncoder.encode(request.password());

        User user = userService.createUser(
                new CreateUserCommand(
                        request.email(),
                        passwordHash,
                        request.name()
                )
        );

        return jwtService.generateToken(user.getId(), user.getEmail());
    }

    public String loginUser(LoginRequest request) {
        User user = userService.getUserByEmail(request.email());

        if(!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return jwtService.generateToken(user.getId(), user.getEmail());
    }
}