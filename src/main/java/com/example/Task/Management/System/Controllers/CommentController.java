package com.example.Task.Management.System.Controllers;

import com.example.Task.Management.System.Controllers.DTO.CommentDto;
import com.example.Task.Management.System.Controllers.Mappers.CommentMapper;
import com.example.Task.Management.System.Enums.Role;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.CommentNotFoundException;
import com.example.Task.Management.System.ExceptionHandler.CustomExceptions.PermissionDeniedException;
import com.example.Task.Management.System.Models.Comment;
import com.example.Task.Management.System.Models.User.User;
import com.example.Task.Management.System.Services.CommentService;
import com.example.Task.Management.System.Services.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    private final TaskService taskService;

    @PostMapping()
    public CommentDto create(@RequestBody @Valid CommentDto commentDto, @AuthenticationPrincipal User user) throws PermissionDeniedException {
        Comment comment = commentMapper.toEntity(commentDto);
        comment.setCreator(user);
        Comment resultComment = commentService.create(comment);
        taskService.addCommentByTaskId(commentDto.getTaskId(), resultComment);
        return commentMapper.toDto(resultComment);
    }

    @DeleteMapping("/{id}")
    public CommentDto delete(@PathVariable Long id) throws PermissionDeniedException, CommentNotFoundException {
        return commentMapper.toDto(commentService.deleteById(id));
    }

    @PatchMapping()
    public CommentDto patch(@RequestBody @Valid CommentDto commentDto) throws PermissionDeniedException, CommentNotFoundException {
        return commentMapper.toDto(commentService.patch(commentMapper.toEntity(commentDto)));
    }




//    @GetMapping
//    public Page<CommentDto> getList(Pageable pageable) {
//        Page<Comment> comments = commentService.getList(pageable);
//        Page<CommentDto> commentDtoPage = comments.map(commentMapper::toDto);
//        return commentDtoPage;
//    }
//
//    @GetMapping("/{id}")
//    public CommentDto getOne(@PathVariable Long id) {
//        Optional<Comment> commentOptional = commentService.getOne(id);
//        CommentDto commentDto = commentMapper.toDto(commentOptional.orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
//        return commentDto;
//    }
//
//    @GetMapping("/by-ids")
//    public List<CommentDto> getMany(@RequestParam List<Long> ids) {
//        List<Comment> comments = commentService.getMany(ids);
//        List<CommentDto> commentDtos = comments.stream()
//                .map(commentMapper::toDto)
//                .toList();
//        return commentDtos;
//    }
//
//    @PostMapping
//    public CommentDto create(@RequestBody @Valid CommentDto dto) {
//        Comment comment = commentMapper.toEntity(dto);
//        Comment resultComment = commentService.create(comment);
//        return commentMapper.toDto(resultComment);
//    }
//
//    @PatchMapping("/{id}")
//    public CommentDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
//        Comment comment = commentRepository.findById(id).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
//
//        CommentDto commentDto = commentMapper.toDto(comment);
//        objectMapper.readerForUpdating(commentDto).readValue(patchNode);
//        commentMapper.updateWithNull(commentDto, comment);
//
//        Comment resultComment = commentService.patch(comment);
//        return commentMapper.toDto(resultComment);
//    }
//
//    @PatchMapping
//    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
//        Collection<Comment> comments = commentRepository.findAllById(ids);
//
//        for (Comment comment : comments) {
//            CommentDto commentDto = commentMapper.toDto(comment);
//            objectMapper.readerForUpdating(commentDto).readValue(patchNode);
//            commentMapper.updateWithNull(commentDto, comment);
//        }
//
//        List<Comment> resultComments = commentService.patchMany(comments);
//        List<Long> ids1 = resultComments.stream()
//                .map(Comment::getId)
//                .toList();
//        return ids1;
//    }
//
//    @DeleteMapping("/{id}")
//    public CommentDto delete(@PathVariable Long id) {
//        Comment comment = commentRepository.findById(id).orElse(null);
//        if (comment != null) {
//            commentService.delete(comment);
//        }
//        return commentMapper.toDto(comment);
//    }
//
//    @DeleteMapping
//    public void deleteMany(@RequestParam List<Long> ids) {
//        commentService.deleteMany(ids);
//    }
}
