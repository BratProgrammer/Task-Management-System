package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.Controllers.DTO.TaskPatchDto;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.PermissionDeniedException;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.TaskNotFoundException;
import com.example.Task.Management.System.Models.Comment;
import com.example.Task.Management.System.Models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    Page<Task> getPageable(Pageable pageable);

    void addCommentByTaskId(Long taskId, Comment comment) throws PermissionDeniedException, TaskNotFoundException;

    Task create(Task task) throws PermissionDeniedException;

    Task findById(Long id) throws TaskNotFoundException;

    void deleteById(Long id) throws PermissionDeniedException, TaskNotFoundException;

    Task patch(Long id, TaskPatchDto taskDto) throws PermissionDeniedException, TaskNotFoundException;

    Task addExecutor(Long taskId, Long userId) throws PermissionDeniedException, TaskNotFoundException;
}
