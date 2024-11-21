package com.example.Task.Management.System.ExceptionHandler.CustomExceptions;

public class PermissionDeniedException extends Exception {
    public PermissionDeniedException(String message) { super(message); }
}
