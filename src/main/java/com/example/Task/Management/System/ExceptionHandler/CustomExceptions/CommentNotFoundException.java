package com.example.Task.Management.System.ExceptionHandler.CustomExceptions;

import jakarta.persistence.EntityNotFoundException;

public class CommentNotFoundException extends EntityNotFoundException {
    public CommentNotFoundException(String message) { super(message); }
}
