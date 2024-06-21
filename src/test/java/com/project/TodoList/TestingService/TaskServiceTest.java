package com.project.TodoList.TestingService;

import com.project.TodoList.common.exception.MainException;
import com.project.TodoList.models.contract.TaskResponce;
import com.project.TodoList.models.entities.TaskEntity;
import com.project.TodoList.models.repositories.TaskReposetory;
import com.project.TodoList.services.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.TodoList.common.enums.ExceptionsCode.OBJECT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    private TaskReposetory taskRepository;

    @InjectMocks
    private TaskService taskService;
    private static TaskEntity taskEntity;
    private static TaskResponce taskResponse;
    private static TaskEntity updatedTask;
    private static List<TaskEntity> expectedEntity;
    private static List<TaskResponce> expectedResponse;
    private static TaskResponce updatedTaskResponse;
    @BeforeAll
    static void initExpectedData() {
        taskEntity = new TaskEntity(1L, "Task", "Content", "inactive");
        updatedTask = new TaskEntity(1L, "New Task", "New Content", "active");

        updatedTaskResponse=new TaskResponce(1L, "New Task", "New Content", "active");
        taskResponse = new TaskResponce(1L, "Task", "Content", "inactive");

        expectedEntity = new ArrayList<TaskEntity>() {{
            add(new TaskEntity(1L, "First Task", "Content of the first task", "inactive"));
            add(new TaskEntity(2L, "Second Task", "Content of the second task", "closed"));
        }};
        expectedResponse = new ArrayList<TaskResponce>() {{
            add(new TaskResponce(1L, "First Task", "Content of the first task", "inactive"));
            add(new TaskResponce(2L, "Second Task", "Content of the second task", "closed"));
        }};
    }

    @Test
    void TaskCreate_TitleTooLong_MainException() {
        TaskEntity taskRequest = new TaskEntity(1L, "Lorem Lorem Lorem Lorem Lorem Lorem Lorem",
                "content", "inactive");
        Assertions.assertThrows(MainException.class, () -> taskService.CreateTask(taskRequest));
    }

    @Test
    void TaskCreate_ContentTooLong_MainException() {
        TaskEntity taskRequest = new TaskEntity(1L, "sddd",
                "content content content content content content content content", "inactive");
        Assertions.assertThrows(MainException.class, () -> taskService.CreateTask(taskRequest));
    }

    @Test
    void GetAllTasks_DataMatchExpected() {
        when(taskRepository.findAll()).thenReturn(expectedEntity);
        var actualResponse = taskService.GetAllTasks();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void TaskCreate_DataMatchExpected() {
        doReturn(taskEntity).when(taskRepository).save(any(TaskEntity.class));

        var savedTask = taskService.CreateTask(taskEntity);

        assertEquals(1L, savedTask);

        verify(taskRepository).save(taskEntity);
    }

    @Test
    void TaskUpdate_DataMatchExpected() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(updatedTask);

        var savedTask = taskService.UpdateTask(updatedTask);
        assertEquals(updatedTaskResponse, savedTask);
        verify(taskRepository).findById(1L);
        verify(taskRepository).save(any(TaskEntity.class));
    }

    @Test
    void TaskUpdate_STATUS_ILLEGAL_ARGUMENT() {
        TaskEntity updatedTask = new TaskEntity(1L, "New Task", "New Content", "unclosed");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));
        Assertions.assertThrows(MainException.class, () -> taskService.UpdateTask(updatedTask));
    }

    @Test
    void TaskGetbyId_ObjectNotFound() {
        when(taskRepository.findById(1L)).thenThrow(new MainException(OBJECT_NOT_FOUND,HttpStatus.NOT_FOUND));
        Assertions.assertThrows(MainException.class,()->taskService.GetTaskById(1L));
    }
    @Test
    void TaskUpdate_ObjectNotFound(){
        when(taskRepository.findById(1L)).thenThrow(new MainException(OBJECT_NOT_FOUND, HttpStatus.NOT_FOUND));
        Assertions.assertThrows(MainException.class, () -> taskService.UpdateTask(updatedTask));
    }
    @Test
    void TaskGetById_DataMatchExpected(){
        doReturn(taskEntity).when(taskRepository).save(any(TaskEntity.class));

        taskService.CreateTask(taskEntity);
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(taskEntity));
        var taskActualResponse=taskService.GetTaskById(1L);
        assertEquals(taskActualResponse,taskResponse);
    }
}
