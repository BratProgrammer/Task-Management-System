package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.CommentNotFoundException;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.PermissionDeniedException;
import com.example.Task.Management.System.Models.Comment;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Page<Comment> getPageable(Pageable pageable);

    Optional<Comment> getOne(Long id);

    List<Comment> getMany(Collection<Long> ids);

    Comment create(Comment dto);

    Comment patch(Comment id) throws PermissionDeniedException, CommentNotFoundException;

    List<Comment> patchMany(Collection<Comment> ids, JsonNode patchNode);

    void delete(Comment id);

    void deleteMany(Collection<Long> ids);

    boolean existById(Long id);

    Comment deleteById(Long id) throws PermissionDeniedException, CommentNotFoundException;

    Comment findById(Long id);
}
