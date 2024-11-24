package com.example.Task.Management.System.Security.Controller.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorizationRequest {

    @NotBlank(message = "Email can't be empty")
    @NotNull(message = "Email can't be null")
    @Email(message = "Email address must be in the format user@example.com")
    private String email;

    @NotBlank(message = "Password can't be empty")
    @NotNull(message = "Password can't be null")
    private String password;

}

