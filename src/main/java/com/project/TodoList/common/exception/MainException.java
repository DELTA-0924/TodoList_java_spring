package com.project.TodoList.common.exception;

import com.project.TodoList.common.enums.ExceptionsCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class MainException extends RuntimeException{
    private ExceptionsCode code;
    private HttpStatus statusCode;
}
