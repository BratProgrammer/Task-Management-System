package com.example.Task.Management.System.Repositories;

import com.example.Task.Management.System.Models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}