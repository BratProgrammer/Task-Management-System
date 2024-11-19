package com.example.Task.Management.System.Repositories;

import com.example.Task.Management.System.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}