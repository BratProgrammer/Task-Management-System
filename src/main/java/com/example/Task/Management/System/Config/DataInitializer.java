package com.example.Task.Management.System.Config;

import com.example.Task.Management.System.Models.User;
import com.example.Task.Management.System.Repositories.AuthorityRepository;
import com.example.Task.Management.System.Repositories.UserRepository;
import com.example.Task.Management.System.Security.Authotity.Authority;
import com.example.Task.Management.System.Security.Authotity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void run(String... args) throws Exception {
        Optional<User> adminOptional = userRepository.findByAuthorities_Role(Role.ROLE_ADMIN);

        if (adminOptional.isEmpty()) {
            User admin = new User();

            Authority authority = authorityRepository.findByRole(Role.ROLE_ADMIN);

            if (authority == null) {
                authority = new Authority(Role.ROLE_ADMIN);
                authority = authorityRepository.save(authority);
            }

            admin.setAuthorities(Set.of(authority));

            admin.setEmail("admin@admin.com");
            admin.setUsername("admin");
            admin.setPassword("admin");


            userRepository.save(admin);
        }
    }
}
