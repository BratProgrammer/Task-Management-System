package com.example.Task.Management.System.Controllers.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddExecutorDto {
    @NotNull
    Long taskId;
    @NotNull
    Long userId;

}
