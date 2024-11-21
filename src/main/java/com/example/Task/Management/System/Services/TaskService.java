package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.TaskNotFoundException;
import com.example.Task.Management.System.Models.Comment;
import com.example.Task.Management.System.Models.Task;
import com.example.Task.Management.System.Repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addCommentByTaskId(Long taskId, Comment comment) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException("Task with id:" + taskId + " not found");
        }

        Task task = taskOptional.get();

        task.addComment(comment);

        taskRepository.save(task);
    }


}
