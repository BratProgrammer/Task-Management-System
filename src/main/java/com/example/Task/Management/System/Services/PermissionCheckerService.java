package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.PermissionDeniedException;
import com.example.Task.Management.System.Models.Comment;
import com.example.Task.Management.System.Models.Task;

public interface PermissionCheckerService {

    void isAdmin() throws PermissionDeniedException;

    void checkAccessToComment(Comment comment) throws PermissionDeniedException;

    void checkAccessToTask(Task task) throws PermissionDeniedException;

    void checkAccessToUserDataById(Long userId) throws PermissionDeniedException;
}
