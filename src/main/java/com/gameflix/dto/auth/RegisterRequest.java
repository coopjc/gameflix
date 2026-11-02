package com.gameflix.dto.auth;

import jakarta.validation.constraints.*;

public record RegisterRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        @Size(max = 64, message = "Password cannot exceed 64 characters")
        String password,

        @NotBlank(message = "Name is required")
        String name
) {}