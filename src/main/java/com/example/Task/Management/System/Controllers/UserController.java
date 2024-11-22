package com.example.Task.Management.System.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    @DeleteMapping("/{id}")
    public void deleteByIdVoid(@PathVariable Long id) {

    }

}

