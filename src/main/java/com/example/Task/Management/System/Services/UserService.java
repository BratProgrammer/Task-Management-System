package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.Models.User.User;
import com.example.Task.Management.System.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteById(Long id) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();



        userRepository.deleteById(id);
    }

}
