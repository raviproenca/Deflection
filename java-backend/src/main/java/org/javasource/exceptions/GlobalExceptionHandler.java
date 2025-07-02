package org.javasource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<String> handleExternalApi(ExternalApiException e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Falha ao consultar serviço externo: " + e.getMessage());
    }

    public ResponseEntity<String> handleInvalidInput(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
