package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.Controllers.DTO.TaskPatchDto;
import com.example.Task.Management.System.Controllers.Mappers.TaskMapper;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.PermissionDeniedException;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.TaskNotFoundException;
import com.example.Task.Management.System.Models.Comment;
import com.example.Task.Management.System.Models.Task;
import com.example.Task.Management.System.Models.User.User;
import com.example.Task.Management.System.Repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final PermissionCheckerService permissionCheckerService;

    private final TaskMapper taskMapper;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addCommentByTaskId(Long taskId, Comment comment) throws PermissionDeniedException {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException("Task with id:" + taskId + " not found");
        }

        Task task = taskOptional.get();

        permissionCheckerService.checkAccessToTask(task);

        task.addComment(comment);

        taskRepository.save(task);
    }


    public Task create(Task task) {
        task.addAllowedUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return taskRepository.save(task);
    }

    public Task findById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException("Task with id:" + id + " not found");
        }

        return taskOptional.get();
    }

    public void deleteById(Long id) throws PermissionDeniedException {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException("Task with id:" + id + " not found");
        }

        permissionCheckerService.checkAccessToTask(taskOptional.get());
        taskRepository.deleteById(id);
    }

    public Task patch(Long id, TaskPatchDto taskDto) throws PermissionDeniedException {
        Task task = findById(id);

        permissionCheckerService.checkAccessToTask(task);

        taskMapper.partialUpdate(taskDto, task);

        return taskRepository.save(task);
    }

}
