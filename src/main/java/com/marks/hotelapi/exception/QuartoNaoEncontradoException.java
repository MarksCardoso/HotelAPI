package com.marks.hotelapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuartoNaoEncontradoException extends RuntimeException {
    public QuartoNaoEncontradoException(Long id) {
        super("Quarto nao encontrado com o id " + id);
    }
}
