package com.project.TodoList.models.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TaskCreateRequest
{

    @NotNull(message = "Title field cannot be null")
    @NotBlank(message = "Title field cannot be null")
    @Schema(    description = "Заголовок задачи",required = true)
    private String title;
    @NotNull(message = "Content field cannot be null")
    @Schema(    description = "содержимое  задачи",required = true)
    private String content;


}
