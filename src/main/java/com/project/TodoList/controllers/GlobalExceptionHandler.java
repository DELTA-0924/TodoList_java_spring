package com.project.TodoList.controllers;

import com.project.TodoList.common.exception.MainException;
import com.project.TodoList.models.contract.Response;
import org.hibernate.ResourceClosedException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGlobalException(Exception e) {;
        return ResponseEntity.internalServerError().body(new Response("Server error", HttpStatus.INTERNAL_SERVER_ERROR));
    }
    @ExceptionHandler(MainException.class)
    public ResponseEntity<Response> handleFieldNullException(MainException e ) {
        return new ResponseEntity<>(new Response (e.getCode().toString(),e.getStatusCode()),e.getStatusCode());
    }


    @ExceptionHandler({ MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Response> handleBadRequestExceptions(Exception ex) {
        var error=extractErrorMessage(ex.getMessage());
        return new  ResponseEntity<>(new Response(error,HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }

    public static String extractErrorMessage(String errorMessage) {
        int endIndex = errorMessage.indexOf('"');
        if (endIndex != -1) {
            return errorMessage.substring(errorMessage.indexOf("[")+1,endIndex -2);
        }
        return errorMessage;
    }

}

