package com.project.TodoList.services;
import com.project.TodoList.common.Mapper;
import com.project.TodoList.common.exception.MainException;
import com.project.TodoList.models.contract.TaskResponce;

import com.project.TodoList.services.validators.TaskValidator;
import lombok.AllArgsConstructor;
import com.project.TodoList.models.entities.TaskEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.project.TodoList.models.repositories.TaskReposetory;

import java.util.*;
import java.util.stream.Collectors;

import static com.project.TodoList.common.enums.ExceptionsCode.ID_FIELD_NULL;
import static com.project.TodoList.common.enums.ExceptionsCode.OBJECT_NOT_FOUND;


@Service
@AllArgsConstructor
public class TaskService {
    private TaskReposetory taskReposetory;
    public Long CreateTask(  TaskEntity taskEntity) {
        TaskValidator.TaskTitleValid().apply(taskEntity);
        TaskValidator.TaskContentValid().apply(taskEntity);
        taskReposetory.save(taskEntity);
        return taskEntity.id;
    }
    public List<TaskResponce> GetAllTasks() {
        var taskEntities = taskReposetory.findAll();
        if(taskEntities.isEmpty()) {
            throw new MainException(OBJECT_NOT_FOUND,HttpStatus.NO_CONTENT);
        }
        var taskResponses=taskEntities.stream().map(Mapper::fromEntityToContact).collect(Collectors.toList());
        return taskResponses;
    }
    public TaskResponce UpdateTask(TaskEntity taskRequest) {
        Long id =Optional.ofNullable(taskRequest.getId()).orElseThrow(()->new MainException(ID_FIELD_NULL,HttpStatus.BAD_REQUEST));
        TaskEntity taskEntity = taskReposetory.findById(id).orElseThrow(()->new MainException(OBJECT_NOT_FOUND,HttpStatus.NOT_FOUND));
            //var task= Mapper.fromContactToEntity(request);
        Optional.ofNullable(taskRequest.getStatus()).ifPresent(t->{
                taskEntity.setStatus(t);
                TaskValidator.TaskStatusValid().apply(taskEntity);
            });
        Optional.ofNullable(taskRequest.getTitle()).ifPresent(t->{
                taskEntity.setTitle(t);
                TaskValidator.TaskTitleValid().apply(taskEntity);
            });
        Optional.ofNullable(taskRequest.getContent()).ifPresent(t->{
                taskEntity.setContent(t);
                TaskValidator.TaskContentValid().apply(taskEntity);
            });
        taskReposetory.save(taskEntity);

        return Mapper.fromEntityToContact( taskEntity);
    }
    public void DeleteTask(Long id) {
        if(!taskReposetory.existsById(id)) {
            throw new MainException(OBJECT_NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        taskReposetory.deleteById(id);
    }
    public TaskResponce GetTaskById(Long id) {
         var taskEntity=taskReposetory.findById(id).orElseThrow(()->new MainException(OBJECT_NOT_FOUND,HttpStatus.NOT_FOUND));
         var taskResponse=Mapper.fromEntityToContact(taskEntity);
         return taskResponse;
    }

}
