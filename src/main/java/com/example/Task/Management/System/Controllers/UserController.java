package com.example.Task.Management.System.Controllers;

import com.example.Task.Management.System.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @DeleteMapping("/{id}")
    public void deleteByIdVoid(@PathVariable Long id) {
        userService.deleteById(id);
    }

//    @DeleteMapping()
//    public void delete(@RequestBody @Valid UserDto userDto) {
//        userService.deleteById(id);
//    }

}

