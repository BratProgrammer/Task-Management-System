package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.Enums.Role;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.PermissionDeniedException;
import com.example.Task.Management.System.Models.Comment;
import com.example.Task.Management.System.Models.Task;
import com.example.Task.Management.System.Models.User.User;
import com.example.Task.Management.System.Security.UserDetails.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PermissionCheckerService {

    public void checkAccessToComment(Comment comment) throws PermissionDeniedException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(userDetails.hasRole(Role.ADMIN) || comment.getCreator().getId().equals(userDetails.getId()))) {
            throw new PermissionDeniedException("Access to comment denied");
        }
    }

    public void checkAccessToTask(Task task) throws PermissionDeniedException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails.hasRole(Role.ADMIN)) {
            return;
        }

        for (User allowedUser : task.getAllowedUsers()) {
            if (allowedUser.getId().equals(userDetails.getId())) {
                return;
            }
        }

        throw new PermissionDeniedException("Access to task denied");
    }

    public void checkAccessToUserDataById(Long userId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails.hasRole(Role.ADMIN) || userDetails.getId().equals(userId)) {
            return;
        }

        throw new PermissionDeniedException("Access to user data denied");

    }

}
