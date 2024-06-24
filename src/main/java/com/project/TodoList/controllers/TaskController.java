package com.project.TodoList.controllers;

import com.project.TodoList.common.TaskMapper;
import com.project.TodoList.models.contract.Response;
import com.project.TodoList.models.contract.TaskCreateRequest;
import com.project.TodoList.models.contract.TaskResponce;

import com.project.TodoList.models.contract.TaskUpdateRequest;
import com.project.TodoList.models.repositories.TaskReposetory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.TodoList.services.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Task endpoints")
public class TaskController {

    private final TaskReposetory taskReposetory;
    private final TaskMapper mapper;
    private TaskService taskService;
    @Operation(summary = "get task",description = "this endpoint got a  task by id which stay in database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<TaskResponce> getTask(@PathVariable Long id) {
        var task=taskService.GetTaskById(id);
        return  ResponseEntity.ok(task);
    }
    @Operation(summary = "list of tasks",description = "this endpoint got a list all tasks which stay in database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "204", description = "List is empty"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping("")
    public ResponseEntity<List<TaskResponce>> taskList(){
        var taskResponse=taskService.GetAllTasks();
        return ResponseEntity.ok(taskResponse);
    }

    @Operation(summary = "Create task",description = "this endpoint set a new object 'task' to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad client request "),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @PostMapping(value = "")
    public ResponseEntity<Response> taskCreate(@Valid @RequestBody TaskCreateRequest request) {
        Long  taskId=taskService.CreateTask(mapper.ContactToEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(taskId.toString(),HttpStatus.CREATED));
    }

    @Operation(summary = "delete task",description = "this endpoint delete a object 'task' from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Response>taskDelete(@PathVariable Long id){
        taskService.DeleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Task deleted",HttpStatus.OK));
    }

    @Operation(summary = "update tasks",description = "this endpoint got a update operation for object 'task' on database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "400", description = "Bad client request "),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @PutMapping("")
    public ResponseEntity<TaskResponce>taskUpdate(@RequestBody TaskUpdateRequest request){
        var taskResponse =taskService.UpdateTask(mapper.UpdateContactToEntity(request));
        return ResponseEntity.ok().body(taskResponse);
    }
}
