package com.example.Task.Management.System.Controllers.Mappers;

import com.example.Task.Management.System.Controllers.DTO.CommentDto;
import com.example.Task.Management.System.Models.Comment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
    Comment toEntity(CommentDto commentDto);

    CommentDto toDto(Comment comment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment partialUpdate(CommentDto commentDto, @MappingTarget Comment comment);

    Comment updateWithNull(CommentDto commentDto, @MappingTarget Comment comment);
}