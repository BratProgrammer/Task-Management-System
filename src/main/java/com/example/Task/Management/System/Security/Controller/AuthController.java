package com.example.Task.Management.System.Security.Controller;

import com.example.Task.Management.System.Security.Controller.DTO.AuthorizationRequest;
import com.example.Task.Management.System.Security.Controller.DTO.RegistrationRequest;
import com.example.Task.Management.System.Security.JWT.JwtUtils;
import com.example.Task.Management.System.Services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthorizationRequest authorizationRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizationRequest.getEmail(), authorizationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(jwtUtil.generateToken(authentication));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegistrationRequest registrationRequest) {
        if (userService.existsByEmail(registrationRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Exists user with same username");
        } else if (userService.existsByEmail(registrationRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Exists user with same email");
        }

        userService.createUser(registrationRequest);

        return ResponseEntity.ok().build();
    }

}
