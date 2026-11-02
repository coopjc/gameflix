package com.gameflix.dto.user;

public record CreateUserCommand(
        String email,
        String passwordHash,
        String name
) {}