package com.project.TodoList.common;
import com.project.TodoList.models.contract.TaskCreateRequest;
import com.project.TodoList.models.contract.TaskResponce;
import com.project.TodoList.models.contract.TaskUpdateRequest;
import com.project.TodoList.models.entities.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface  TaskMapper{
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    @Mapping(source = "title",target = "title")
    @Mapping(source = "content",target = "content")
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "status",ignore = true)
    TaskEntity ContactToEntity(TaskCreateRequest request);
    TaskEntity UpdateContactToEntity(TaskUpdateRequest request);
    TaskResponce EntityToContact(TaskEntity entity);
}