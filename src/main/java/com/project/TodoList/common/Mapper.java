package com.project.TodoList.common;

import com.project.TodoList.common.exception.MainException;
import com.project.TodoList.models.contract.TaskCreateRequest;
import com.project.TodoList.models.contract.TaskResponce;

import com.project.TodoList.models.contract.TaskUpdateRequest;

import com.project.TodoList.models.entities.TaskEntity;
import org.springframework.http.HttpStatus;

import static com.project.TodoList.common.enums.ExceptionsCode.*;
import static com.project.TodoList.common.enums.Status.inactive;

public class Mapper {
    public static TaskEntity fromContactToEntity(TaskCreateRequest taskContact){
        TaskEntity task=TaskEntity.builder()
                .title(taskContact.getTitle().orElseThrow(()->new MainException(TITLE_NULL, HttpStatus.BAD_REQUEST)))
                .content(taskContact.getContent().orElseThrow(()->new MainException(CONTENT_NULL,HttpStatus.BAD_REQUEST)))
                .status(inactive.toString())
                .build();
        return task;
    }
    public static TaskEntity fromContactToEntity(TaskUpdateRequest taskContact){
        TaskEntity task=new TaskEntity();
        task.setId(taskContact.getId());
        taskContact.getTitle().ifPresent(task::setTitle);
        taskContact.getContent().ifPresent(task::setContent);
        taskContact.getStatus().ifPresent(task::setStatus);
        return task;
    }
    public static TaskResponce fromEntityToContact(TaskEntity taskEntity) {
        TaskResponce task = new TaskResponce(taskEntity.id,
                taskEntity.title,
                taskEntity.content,
                taskEntity.status);
        return task;
    }
}
