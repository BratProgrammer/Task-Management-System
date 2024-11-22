package com.example.Task.Management.System.Controllers.DTO;

import com.example.Task.Management.System.Enums.TaskPriority;
import com.example.Task.Management.System.Enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.Task.Management.System.Models.Task}
 */
@Value
public class TaskDto implements Serializable {
    Long id;
    @NotNull
    TaskStatus status;
    @NotNull
    TaskPriority priority;
    @NotNull
    @NotBlank
    String header;
    String description;
    List<CommentDto> comments;
}