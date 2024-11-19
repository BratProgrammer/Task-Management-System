package com.example.Task.Management.System.Repositories;

import com.example.Task.Management.System.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}