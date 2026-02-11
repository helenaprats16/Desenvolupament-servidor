package edu.alumno.helena.api_rest_bd_pelicula.exception;

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

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404: entidad no encontrada
    @ExceptionHandler({ DirectorNotFoundException.class, PeliculaNotFoundException.class, GeneroNotFoundException.class })
    public ResponseEntity<ApiError> handleNotFound(RuntimeException ex, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, extractErrorCode(ex, "NOT_FOUND"), ex.getMessage(),
                request.getRequestURI(), null);
    }

    // 400: parametros invalidos (paginacion/ordenacion/etc.)
    @ExceptionHandler({ BadRequestException.class, IllegalArgumentException.class })
    public ResponseEntity<ApiError> handleBadRequest(RuntimeException ex, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, extractErrorCode(ex, "BAD_REQUEST"), ex.getMessage(),
                request.getRequestURI(), null);
    }

    // 400: errores de validacion en el body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        // Mapeamos campo -> mensaje para que el cliente lo muestre
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
        return buildError(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", "Validacion de campos",
                request.getRequestURI(), fieldErrors);
    }

    // 400: errores de validacion en parametros
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getConstraintViolations().forEach(cv -> fieldErrors.put(cv.getPropertyPath().toString(), cv.getMessage()));
        return buildError(HttpStatus.BAD_REQUEST, "PARAM_VALIDATION_ERROR", "Validacion de parametros",
            request.getRequestURI(), fieldErrors);
    }

    // 409: problemas de integridad (FK, UNIQUE, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        return buildError(HttpStatus.CONFLICT, "DATA_INTEGRITY_VIOLATION", "Violacion de integridad de datos",
                request.getRequestURI(), null);
    }

    // 500: fallback para errores no controlados
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleGeneric(RuntimeException ex, HttpServletRequest request) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", ex.getMessage(),
                request.getRequestURI(), null);
    }

    // Constructor de respuesta de error
    private ResponseEntity<ApiError> buildError(HttpStatus status, String errorCode, String message, String path,
            Map<String, String> fieldErrors) {
        // ApiError unifica el formato de errores en toda la API
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
