package com.example.Task.Management.System.Controllers.Mappers;

import com.example.Task.Management.System.Controllers.DTO.TaskDto;
import com.example.Task.Management.System.Controllers.DTO.TaskPatchDto;
import com.example.Task.Management.System.Models.Task;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {
    Task toEntity(TaskDto taskDto);

    TaskDto toDto(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task partialUpdate(TaskDto taskDto, @MappingTarget Task task);

    Task toEntity(TaskPatchDto taskPatchDto);

    TaskPatchDto toDto1(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task partialUpdate(TaskPatchDto taskPatchDto, @MappingTarget Task task);
}