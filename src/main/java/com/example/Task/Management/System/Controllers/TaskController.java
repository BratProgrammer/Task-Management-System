package com.example.Task.Management.System.Controllers;

import com.example.Task.Management.System.Controllers.DTO.TaskDto;
import com.example.Task.Management.System.Controllers.DTO.TaskPatchDto;
import com.example.Task.Management.System.Controllers.Mappers.TaskMapper;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.PermissionDeniedException;
import com.example.Task.Management.System.Models.Task;
import com.example.Task.Management.System.Services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskMapper taskMapper;

    private final TaskService taskService;

    public TaskDto create(@RequestBody @Valid TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        return taskMapper.toDto(taskService.create(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws PermissionDeniedException {
        taskService.deleteById(id);
        return new ResponseEntity<>("Task deleted", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public TaskDto patch(@PathVariable Long id, @RequestBody @Valid TaskPatchDto patchDto) throws PermissionDeniedException {
        return taskMapper.toDto(taskService.patch(id, patchDto));
    }


}

