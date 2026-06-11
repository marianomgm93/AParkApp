package com.equipo7.AParkApp.common.model.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.function.EntityResponse;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityAlreadyExistsEx.class)
    public ResponseEntity<ExceptionDto> EntityAlreadyExists(EntityAlreadyExistsEx e) {


        return buildResponse(HttpStatus.BAD_REQUEST, "Entity Already Exists");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ExceptionDto> BadArguments(MethodArgumentNotValidException ex) {

        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ExceptionDto> NotFound(EntityNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }


    /////////AUX
    private ResponseEntity<ExceptionDto> buildResponse(HttpStatus status, String msg) {
        ExceptionDto error = new ExceptionDto(msg, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }
}
