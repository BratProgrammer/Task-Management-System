package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.Controllers.DTO.UserDto;
import com.example.Task.Management.System.Controllers.Mappers.UserMapper;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.UserNotFoundException;
import com.example.Task.Management.System.Models.User;
import com.example.Task.Management.System.Repositories.UserRepository;
import com.example.Task.Management.System.Security.Authotity.Authority;
import com.example.Task.Management.System.Security.Authotity.Role;
import com.example.Task.Management.System.Security.Controller.DTO.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void createUser(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        user.setAuthorities(Set.of(new Authority(Role.ROLE_USER)));

        userRepository.save(user);
    }
}
