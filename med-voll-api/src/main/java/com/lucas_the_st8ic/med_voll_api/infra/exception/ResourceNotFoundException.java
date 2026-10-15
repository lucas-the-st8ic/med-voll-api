package com.lucas_the_st8ic.med_voll_api.infra.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
