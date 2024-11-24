package com.example.Task.Management.System.Security.Controller.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotNull(message = "Username can't be null")
    @NotBlank(message = "Username can't be empty")
    private String username;

    @NotNull(message = "Password can't be null")
    @NotBlank(message = "Password can't be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Email can't be null")
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email address must be in the format user@example.com")
    private String email;

}
