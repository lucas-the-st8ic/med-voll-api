package com.lucas_the_st8ic.med_voll_api.infra;


import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind
            .annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFoundException() {

        return ResponseEntity.notFound().build();
    }

    @org.springframework.web.bind
            .annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequestException () {

        return ResponseEntity.badRequest().build();
    }
}
