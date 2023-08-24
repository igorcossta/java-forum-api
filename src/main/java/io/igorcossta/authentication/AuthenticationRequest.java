package io.igorcossta.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
        @Email(message = "Username must be an email")
        @NotBlank(message = "Username must not be blank")
        @Size(min = 10, max = 60, message = "Username must have at least 10 characters (max 60)")
        String username,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 4, message = "Password must have at least 4 characters")
        String password
) {
}
