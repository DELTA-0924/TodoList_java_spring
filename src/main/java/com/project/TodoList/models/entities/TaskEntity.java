package com.project.TodoList.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name="Task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  Long id;
    @NonNull
    public String title;
    @NonNull
    public String content;
    @NonNull
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
