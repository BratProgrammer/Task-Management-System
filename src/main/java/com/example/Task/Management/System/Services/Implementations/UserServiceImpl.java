package com.example.Task.Management.System.Services.Implementations;

import com.example.Task.Management.System.Controllers.DTO.UserDto;
import com.example.Task.Management.System.Controllers.Mappers.UserMapper;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.UserNotFoundException;
import com.example.Task.Management.System.Models.User;
import com.example.Task.Management.System.Repositories.AuthorityRepository;
import com.example.Task.Management.System.Repositories.UserRepository;
import com.example.Task.Management.System.Security.Authotity.Authority;
import com.example.Task.Management.System.Security.Authotity.Role;
import com.example.Task.Management.System.Security.Controller.DTO.RegistrationRequest;
import com.example.Task.Management.System.Services.PermissionCheckerService;
import com.example.Task.Management.System.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PermissionCheckerService permissionCheckerService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

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
        return user;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void createUser(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        Authority authority = authorityRepository.findByRole(Role.ROLE_USER);

        if (authority == null) {
            user.setAuthorities(Set.of(new Authority(Role.ROLE_USER)));
        } else {
            user.setAuthorities(Set.of(authority));
        }

        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
