package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.Enums.Role;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.PermissionDeniedException;
import com.example.Task.Management.System.Models.Comment;
import com.example.Task.Management.System.Models.Task;
import com.example.Task.Management.System.Models.User.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PermissionCheckerService {

    public void checkPermissionForComment(Comment comment) throws PermissionDeniedException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(user.hasRole(Role.ADMIN) || Objects.equals(comment.getCreator().getId(), user.getId()))) {
            throw new PermissionDeniedException("Access to comment denied");
        }
    }

    public void checkPermissionForTask(Task task) throws PermissionDeniedException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.hasRole(Role.ADMIN)) {
            return;
        }

        for (User allowedUser : task.getAllowedUsers()) {
            if (allowedUser.equals(user)) {
                return;
            }
        }

        throw new PermissionDeniedException("Access to task denied");
    }

}
