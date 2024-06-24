package com.project.TodoList.common.enums;

public enum ExceptionsCode {
    // Клиентские ошибки
    ID_FIELD_NULL(1, "ID field cannot be null"),
    ID_CONTENT_NULL(2, "ID field cannot be null"),
    TITLE_NULL(3, "Title field cannot be null"),
    CONTENT_NULL(4, "Content field cannot be null"),
    TASK_NULL(5, "Task  cannot be null"),
    STATUS_ILLEGAL_ARGUMENT(6,"Status field invalid "),
    STATUS_NULL(7,"Status field cannot be null"),
    CONTENT_TOO_LONG(7, "Content length exceeds the maximum allowed length"),
    TITLE_TOO_LONG(8, "Title length exceeds the maximum allowed length"),
    INVALID_INPUT(9, "Invalid input provided"),
    PAGE_NOT_FOUND(10, "Page not found"),
    FORBIDDEN(11, "Forbidden"),
    OBJECT_NOT_FOUND(12, "Object not found"),
    // Серверные ошибки
    INTERNAL_SERVER_ERROR(12, "Internal server error"),
    SERVICE_UNAVAILABLE(13, "Service unavailable");

    private final int code;
    private final String message;

    ExceptionsCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

