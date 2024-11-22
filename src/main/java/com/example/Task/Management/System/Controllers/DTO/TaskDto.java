package com.example.Task.Management.System.Controllers.DTO;

import com.example.Task.Management.System.Enums.TaskPriority;
import com.example.Task.Management.System.Enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.Task.Management.System.Models.Task}
 */
public record TaskDto(
        Long id,
        @NotNull(message = "status not specified")
        TaskStatus status,
        @NotNull(message = "priority not specified")
        TaskPriority priority,
        @NotNull(message = "header not specified")
        @NotBlank(message = "header is blank")
        String header,
        String description,
        List<CommentDto> comments) implements Serializable {
}