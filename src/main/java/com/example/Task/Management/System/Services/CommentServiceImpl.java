package com.example.Task.Management.System.Services;

import com.example.Task.Management.System.Models.Comment;
import com.example.Task.Management.System.Repositories.CommentRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;



    @Override
    public Page<Comment> getList(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Optional<Comment> getOne(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getMany(Collection<Long> ids) {
        return commentRepository.findAllById(ids);
    }

    @Override
    public Comment create(Comment dto) {
        return commentRepository.save(dto);
    }

    @Override
    public Comment patch(Comment comment, JsonNode patchNode) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> patchMany(Collection<Comment> comments, JsonNode patchNode) {
        return commentRepository.saveAll(comments);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void deleteMany(Collection<Long> ids) {
        commentRepository.deleteAllById(ids);
    }

    @Override
    public boolean existById(Long id) {
        return commentRepository.existsById(id);
    }

}
