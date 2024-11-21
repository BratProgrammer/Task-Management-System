package com.example.Task.Management.System.Controllers.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.Task.Management.System.Models.Comment}
 */
@Value
public class CommentDto implements Serializable {
    @NotNull
    Long id;
    @NotNull
    @NotBlank
    String text;
    @NotNull
    @NotBlank
    String lastUsername;
    @NotNull
    Long taskId;
}