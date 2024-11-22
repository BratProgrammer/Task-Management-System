package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.Controllers.DTO.UserDto;
import com.example.Task.Management.System.Controllers.Mappers.UserMapper;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.UserNotFoundException;
import com.example.Task.Management.System.Models.User.User;
import com.example.Task.Management.System.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PermissionCheckerService permissionCheckerService;

    private final UserMapper userMapper;

    private final UserDetailsService userDetailsService;

//    public void delete(User user) {
//        permissionCheckerService.checkAccessToUserDataById(user.getId());
//        userRepository.delete(user);
//    }

    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with id:" + id + " not found");
        }

        return userOptional.get();
    }

    public void deleteById(Long id) {
        permissionCheckerService.checkAccessToUserDataById(id);
        userRepository.deleteById(id);
    }

    public User patchUserData(UserDto userDto) {
        User user = findById(userDto.id());
        permissionCheckerService.checkAccessToUserDataById(user.getId());
        userMapper.partialUpdate(userDto, user);
        user = userRepository.save(user);

        // TODO update SecurityContextHolder

        return user;
    }

}
