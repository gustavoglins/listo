package com.listo.exceptions.handle;

import com.listo.exceptions.TaskNotFoundException;
import com.listo.exceptions.UserNotFoundException;
import com.listo.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // --------------------------------- Method to facilite create ExceptionResponses ----------------------------------
    private ResponseEntity<ExceptionResponse> buildResponse(Exception exception, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(exceptionResponse);
    }

    // ------------------------------------------------ Generic Exception ----------------------------------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericExceptions(Exception exception, WebRequest request){
        return buildResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    // ------------------------------------------------- User Exceptions -----------------------------------------------
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundExceptions(UserNotFoundException exception, WebRequest request) {
        return buildResponse(exception, HttpStatus.NOT_FOUND, request);
    }

    // ------------------------------------------------- Task Exceptions -----------------------------------------------
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleTaskNotFoundExceptions(TaskNotFoundException exception, WebRequest request){
        return buildResponse(exception, HttpStatus.NOT_FOUND, request);
    }
}
