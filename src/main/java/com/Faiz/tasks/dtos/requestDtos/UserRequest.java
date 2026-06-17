package com.Faiz.tasks.dtos.requestDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,

        @NotBlank
        @Email(message = "Invalid email format")
        String email,

        @NotBlank
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password
) {
}
