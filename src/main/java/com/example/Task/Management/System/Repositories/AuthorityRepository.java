package com.example.Task.Management.System.Repositories;

import com.example.Task.Management.System.Security.Authotity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}