package com.lucas_the_st8ic.med_voll_api.infra.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
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
    public ResponseEntity badRequestException (
            MethodArgumentNotValidException exception) {

        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream()
                .map(DataArgumentNotValid::new).toList());
    }


    private record DataArgumentNotValid(String campo, String mensagem) {

        public DataArgumentNotValid(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
