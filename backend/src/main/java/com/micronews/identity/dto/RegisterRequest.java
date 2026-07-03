package com.micronews.identity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Login cannot be empty") String login,

        @NotBlank(message = "Password cannot be empty") @Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter") @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter") @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit") @Pattern(regexp = ".*[^a-zA-Z0-9].*", message = "Password must contain at least one special character") String password,

        String firstname,
        String surname,
        String city) {
}
