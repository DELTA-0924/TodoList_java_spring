package com.project.TodoList.models.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
@Getter
@AllArgsConstructor
public class TaskUpdateRequest {
    @NotNull
    @Schema(    description = "Индефиктор задачи",required = true)
    private Long id;
    @Schema(    description = "Заголовок задачи")
    private String title;
    @Schema(    description = "содержимое  задачи")
    private String content;
    @Schema(    description = "статус  задачи (по умолчанию inactive)" )
    private String status;
}
