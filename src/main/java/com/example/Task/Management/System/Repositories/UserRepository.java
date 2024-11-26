package com.example.Task.Management.System.Repositories;

import com.example.Task.Management.System.Models.User;
import com.example.Task.Management.System.Security.Authotity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByEmail(Object unknownAttr1);

    Optional<User> findByAuthorities_Role(Role role);
}