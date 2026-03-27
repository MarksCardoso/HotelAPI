package com.marks.hotelapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException extends RuntimeException {
    @ExceptionHandler(QuartoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleNaoEncontrado(QuartoNaoEncontradoException erro){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                404,
                "Not Found",
                erro.getMessage(),
                "/api/quartos",
                LocalDateTime.now()
        ));
    }
}
