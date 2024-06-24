package com.project.TodoList.models.repositories;

import com.project.TodoList.models.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskReposetory extends JpaRepository<TaskEntity, Long> {
//    @Transactional
//    @Modifying
//    @Query("update TaskEntity t set t.status=:status where t.id=:Id")
//    void changeStatusById(@Param("Id") Long Id,@Param("status") String status);
}
