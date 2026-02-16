package edu.alumno.helena.api_rest_bd_pelicula.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import edu.alumno.helena.api_rest_bd_pelicula.exception.BadRequestException;
import edu.alumno.helena.api_rest_bd_pelicula.exception.DirectorNotFoundException;
import edu.alumno.helena.api_rest_bd_pelicula.exception.PeliculaNotFoundException;
import edu.alumno.helena.api_rest_bd_pelicula.exception.GeneroNotFoundException;
import edu.alumno.helena.api_rest_bd_pelicula.exception.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404: entitat no trobada
    @ExceptionHandler({ DirectorNotFoundException.class, PeliculaNotFoundException.class, GeneroNotFoundException.class })
    public ResponseEntity<ApiError> handleNotFound(RuntimeException ex, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, extractErrorCode(ex, "NOT_FOUND"), ex.getMessage(),
                request.getRequestURI(), null);
    }

    // 400: paràmetres invàlids (paginació, ordenació, etc.)
    @ExceptionHandler({ BadRequestException.class, IllegalArgumentException.class })
    public ResponseEntity<ApiError> handleBadRequest(RuntimeException ex, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, extractErrorCode(ex, "BAD_REQUEST"), ex.getMessage(),
                request.getRequestURI(), null);
    }

    // 400: errors de validació en el body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        // Mapegem camp -> missatge per a que el client ho mostre
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
        return buildError(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", "Validació de camps",
                request.getRequestURI(), fieldErrors);
    }

    // 400: errors de validació en paràmetres
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getConstraintViolations().forEach(cv -> fieldErrors.put(cv.getPropertyPath().toString(), cv.getMessage()));
        return buildError(HttpStatus.BAD_REQUEST, "PARAM_VALIDATION_ERROR", "Validació de paràmetres",
            request.getRequestURI(), fieldErrors);
    }

    // 409: problemes d'integritat (FK, UNIQUE, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        return buildError(HttpStatus.CONFLICT, "DATA_INTEGRITY_VIOLATION", "Violació d'integritat de dades",
                request.getRequestURI(), null);
    }

    // 500: fallback per a errors no controlats
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleGeneric(RuntimeException ex, HttpServletRequest request) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", ex.getMessage(),
                request.getRequestURI(), null);
    }

    // Constructor de resposta d'error
    private ResponseEntity<ApiError> buildError(HttpStatus status, String errorCode, String message, String path,
            Map<String, String> fieldErrors) {
        // ApiError unifica el format d'errors en tota l'API
        ApiError error = new ApiError(LocalDateTime.now(), status.value(), status.getReasonPhrase(), errorCode,
                message, path, fieldErrors);
        return ResponseEntity.status(status).body(error);
    }

    private String extractErrorCode(RuntimeException ex, String fallback) {
        if (ex instanceof BadRequestException badRequest) {
            return badRequest.getErrorCode() != null ? badRequest.getErrorCode() : fallback;
        }
        if (ex instanceof DirectorNotFoundException notFound) {
            return notFound.getErrorCode() != null ? notFound.getErrorCode() : fallback;
        }
        if (ex instanceof PeliculaNotFoundException notFound) {
            return notFound.getErrorCode() != null ? notFound.getErrorCode() : fallback;
        }
        if (ex instanceof GeneroNotFoundException notFound) {
            return notFound.getErrorCode() != null ? notFound.getErrorCode() : fallback;
        }
        return fallback;
    }
}
