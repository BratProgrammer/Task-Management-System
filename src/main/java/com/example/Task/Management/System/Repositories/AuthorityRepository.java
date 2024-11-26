package com.example.Task.Management.System.Repositories;

import com.example.Task.Management.System.Security.Authotity.Authority;
import com.example.Task.Management.System.Security.Authotity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByRole(Role role);
}