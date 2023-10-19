package com.smsdispatcher.infrastructure;

import com.smsdispatcher.infrastructure.rest.google.GoogleProviderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ExceptionManager {

    @ExceptionHandler(GoogleProviderException.class)
    public final ResponseEntity<String> handle(GoogleProviderException googleProviderException) {
        return ResponseEntity.internalServerError()
                .body(googleProviderException.getMessage());
    }
}
