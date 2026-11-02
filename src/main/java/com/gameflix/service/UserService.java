package com.gameflix.service;

import com.gameflix.dto.user.CreateUserCommand;
import com.gameflix.exception.user.UserAlreadyExistsException;
import com.gameflix.exception.user.UserNotFoundException;
import com.gameflix.model.User;
import com.gameflix.repository.UserRepository;
import com.gameflix.security.JwtService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public User getCurrentUser(Jwt token) {
        UUID id = jwtService.extractUserId(token);
        return getUserById(id);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User createUser(CreateUserCommand createUserCommand) {
        if (userRepository.existsByEmail(createUserCommand.email())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = new User();

        user.setEmail(createUserCommand.email());
        user.setPasswordHash(createUserCommand.passwordHash());
        user.setName(createUserCommand.name());

        return userRepository.save(user);
    }
}