package com.smsdispatcher.infrastructure;

import com.smsdispatcher.application.NetworkSubscriberNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ExceptionManager {

    @ExceptionHandler(NetworkSubscriberNotFound.class)
    public final ResponseEntity<Integer> handle(NetworkSubscriberNotFound subscriberNotFound) {
        return ResponseEntity.notFound().build();
    }
}
