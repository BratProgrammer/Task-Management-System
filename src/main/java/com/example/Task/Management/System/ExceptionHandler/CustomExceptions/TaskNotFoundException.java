package com.example.Task.Management.System.ExceptionHandler.CustomExceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
