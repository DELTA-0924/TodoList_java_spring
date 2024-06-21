package com.project.TodoList.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name="Task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  Long id;
    @Column(name="title", nullable = false)
    public String title;
    @Column(name="content", nullable = false)
    public String content;
    @Column(name="status", nullable = false)
    public String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, status);
    }
}
