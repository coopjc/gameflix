package com.gameflix.exception;

import com.gameflix.exception.auth.InvalidCredentialsException;
import com.gameflix.exception.auth.UnauthorizedException;
import com.gameflix.exception.game.GameNotFoundException;
import com.gameflix.exception.user.UserAlreadyExistsException;
import com.gameflix.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Global Exceptions

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();

        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.name());

        ex.getBindingResult().getFieldErrors().forEach(error ->
                response.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Auth Exceptions

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        Map<String, String> response = new HashMap<>();

        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.name());
        response.put("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, String>> handleUnauthorizedException(UnauthorizedException ex) {
        Map<String, String> response = new HashMap<>();

        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.name());
        response.put("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Game Exceptions

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleGameNotFoundException(GameNotFoundException ex) {
        Map<String, String> response = new HashMap<>();

        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.name());
        response.put("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // User Exceptions

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();

        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.name());
        response.put("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> response = new HashMap<>();

        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.name());
        response.put("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}