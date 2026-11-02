package com.gameflix.controller;

import com.gameflix.dto.user.UserResponse;
import com.gameflix.model.User;
import com.gameflix.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal Jwt authToken) {
        User user = userService.getCurrentUser(authToken);
        return new ResponseEntity<>(UserResponse.from(user), HttpStatus.OK);
    }
}