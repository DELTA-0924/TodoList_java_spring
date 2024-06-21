package com.project.TodoList.controllers;

import com.project.TodoList.common.exception.MainException;
import com.project.TodoList.models.contract.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGlobalException(Exception e, WebRequest request) {;
        return ResponseEntity.internalServerError().body(new Response("Server error", HttpStatus.INTERNAL_SERVER_ERROR));
    }
    @ExceptionHandler(MainException.class)
    public ResponseEntity<Response> handleFieldNullException(MainException e, WebRequest request) {
        return new ResponseEntity<>(new Response (e.getCode().toString(),e.getStatusCode()),e.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response>  onConstraintValidationException(ConstraintViolationException e,WebRequest request) {
       return ResponseEntity.badRequest().body(new Response(e.getMessage(),HttpStatus.BAD_REQUEST));
    }
}

