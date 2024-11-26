package com.example.Task.Management.System.Controllers;

import com.example.Task.Management.System.Controllers.DTO.AddExecutorDto;
import com.example.Task.Management.System.Controllers.DTO.TaskDto;
import com.example.Task.Management.System.Controllers.DTO.TaskPatchDto;
import com.example.Task.Management.System.Controllers.Mappers.TaskMapper;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.PermissionDeniedException;
import com.example.Task.Management.System.Models.Task;
import com.example.Task.Management.System.Services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskMapper taskMapper;

    private final TaskService taskService;

    @PostMapping()
    public TaskDto create(@RequestBody @Valid TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        return taskMapper.toDto(taskService.create(task));
    }

    @PostMapping("/add_executor")
    public TaskDto addExecutor(@RequestBody @Valid AddExecutorDto addExecutorDto) {
        return taskMapper.toDto(taskService.addExecutor(addExecutorDto.getTaskId(), addExecutorDto.getUserId()));
    }

    @GetMapping("/{id}")
    public TaskDto findById(@PathVariable Long id) {
        return taskMapper.toDto(taskService.findById(id));
    }

    @GetMapping("/all")
    public Page<TaskDto> getList(Pageable pageable) {
        Page<Task> comments = taskService.getPageable(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("creationDateTime").descending()));
        return comments.map(taskMapper::toDto);
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

