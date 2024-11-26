package com.example.Task.Management.System.Repositories;

import com.example.Task.Management.System.Security.Authotity.Authority;
import com.example.Task.Management.System.Security.Authotity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByRole(Role role);
}