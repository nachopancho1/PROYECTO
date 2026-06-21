package com.tienda.ms_pedidos.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap; import java.util.Map;

@ControllerAdvice public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, String> r = new HashMap<>(); r.put("error", ex.getMessage());
        return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
    }
}