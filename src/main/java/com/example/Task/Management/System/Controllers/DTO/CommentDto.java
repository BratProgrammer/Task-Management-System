package com.example.Task.Management.System.Controllers.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.Task.Management.System.Models.Comment}
 */
public record CommentDto(
        Long id,
        @NotNull
        @NotBlank
        String text,
        String lastUsername,

        LocalDateTime creationDateTime,
        @NotNull
        Long taskId) implements Serializable {
}