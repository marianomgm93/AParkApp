package com.equipo7.AParkApp.common.model.exceptions;

import com.equipo7.AParkApp.feature.parkingSpot.ParkingSpotUnavailableException;
import com.equipo7.AParkApp.feature.ticket.exception.InvalidAmountException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            EntityAlreadyExistsEx.class,
            InvalidAmountException.class,
            IllegalStateException.class,
            ParkingSpotUnavailableException.class
    })
    public ResponseEntity<ExceptionDto> handleBadRequestExceptions(RuntimeException e) {


        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ExceptionDto> BadArguments(MethodArgumentNotValidException ex) {

        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ExceptionDto> NotFound(EntityNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadRequestEx.class)
    ResponseEntity<ExceptionDto> BadRequest(BadRequestEx ex) {

        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }


    /// //////AUX
    private ResponseEntity<ExceptionDto> buildResponse(HttpStatus status, String msg) {
        ExceptionDto error = new ExceptionDto(msg, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }
}
