package com.example.Task.Management.System.Controllers.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.example.Task.Management.System.Models.User.User}
 */
public record UserDto(
        @NotNull(message = "ID not specified")
        Long id,
        @NotNull(message = "Username not specified")
        @NotBlank(message = "Username is blank")
        String username,
        @NotNull(message = "Email not specified")
        @NotBlank(message = "Email is blank")
        String email,
        @NotNull(message = "Password not specified")
        @NotBlank(message = "Password is blank")
        String password) implements Serializable {  }