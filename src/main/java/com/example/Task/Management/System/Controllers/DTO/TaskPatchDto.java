package com.example.Task.Management.System.Controllers.DTO;

import com.example.Task.Management.System.Enums.TaskPriority;
import com.example.Task.Management.System.Enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.Task.Management.System.Models.Task}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public record TaskPatchDto(
        @NotNull(message = "Status not specified") TaskStatus status,
        @NotNull(message = "Priority not specified") TaskPriority priority,
        @NotNull(message = "Header not specified") @NotBlank(message = "Header is blank") String header) implements Serializable {
}