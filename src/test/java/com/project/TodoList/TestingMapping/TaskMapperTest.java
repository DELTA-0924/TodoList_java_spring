package com.project.TodoList.TestingMapping;

import com.project.TodoList.common.TaskMapper;
import com.project.TodoList.common.TaskMapper;
import com.project.TodoList.models.contract.TaskCreateRequest;
import com.project.TodoList.models.contract.TaskResponce;
import com.project.TodoList.models.contract.TaskUpdateRequest;
import com.project.TodoList.models.entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
public class TaskMapperTest {

    @Test
    public void shouldMapContactToEntity(){
        TaskCreateRequest taskCreateRequest = new TaskCreateRequest("Rainbow","six seage");
        TaskEntity taskEntity= TaskMapper.INSTANCE.ContactToEntity(taskCreateRequest);
        assertThat(taskEntity).isNotNull();
        assertThat(taskEntity.getTitle()).isEqualTo("Rainbow");
        assertThat(taskEntity.getContent()).isEqualTo("six seage");

    }
    @Test
    public void shouldMapUpdateContactToEntity(){
        TaskUpdateRequest taskUpdateRequest = new TaskUpdateRequest(1L,"Rainbow","six seage","closed");
        TaskEntity taskEntity= TaskMapper.INSTANCE.UpdateContactToEntity(taskUpdateRequest);
        assertThat(taskEntity).isNotNull();
        assertThat(taskEntity.getTitle()).isEqualTo("Rainbow");
        assertThat(taskEntity.getContent()).isEqualTo("six seage");

    }
    @Test
    public void shouldMapEntityContact(){
        TaskEntity taskEntity = TaskEntity.builder().id(1L).title("Rainbow").content("six seage").status("closed").build();
        TaskResponce taskResponce=TaskMapper.INSTANCE.EntityToContact(taskEntity);

        assertThat(taskResponce).isNotNull();
        assertThat(taskResponce.id()).isEqualTo(1L);
        assertThat(taskResponce.title()).isEqualTo("Rainbow");
        assertThat(taskResponce.content()).isEqualTo("six seage");
        assertThat(taskResponce.status()).isEqualTo("closed");
    }

}
