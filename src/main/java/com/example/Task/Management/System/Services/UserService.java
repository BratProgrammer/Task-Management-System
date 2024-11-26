package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.Controllers.DTO.UserDto;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.UserNotFoundException;
import com.example.Task.Management.System.Models.User;
import com.example.Task.Management.System.Security.Controller.DTO.RegistrationRequest;

public interface UserService {

    User findById(Long id) throws UserNotFoundException;

    void deleteById(Long id);

    User patchUserData(UserDto userDto) throws UserNotFoundException;

    boolean existsByEmail(String email);

    void createUser(RegistrationRequest registrationRequest);
}
