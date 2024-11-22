package com.example.Task.Management.System.ExceptionHandler.CustomExceptions;

import jakarta.persistence.EntityNotFoundException;

public class PermissionDeniedException extends EntityNotFoundException {
    public PermissionDeniedException(String message) { super(message); }
}
