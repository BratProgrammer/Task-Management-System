package com.example.Task.Management.System.Controllers;

import com.example.Task.Management.System.Controllers.DTO.UserDto;
import com.example.Task.Management.System.Controllers.Mappers.UserMapper;
import com.example.Task.Management.System.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;


    @DeleteMapping("/{id}")
    public void deleteByIdVoid(@PathVariable Long id) {
        userService.deleteById(id);
    }


    @PatchMapping()
    public UserDto patch(@RequestBody UserDto userDto) {
        return userMapper.toDto(userService.patchUserData(userDto));
    }

    @GetMapping("/{id}")
    public UserDto userDto(@PathVariable Long id) {
        return userMapper.toDto(userService.findById(id));
    }

}

