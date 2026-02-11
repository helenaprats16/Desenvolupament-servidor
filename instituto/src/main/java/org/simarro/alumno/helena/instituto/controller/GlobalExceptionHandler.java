package org.simarro.alumno.helena.instituto.controller;

import java.util.HashMap;
import java.util.Map;

import org.simarro.alumno.helena.instituto.exception.BindingResultErrorsResponse;
import org.simarro.alumno.helena.instituto.exception.BindingResultException;
import org.simarro.alumno.helena.instituto.exception.CustomErrorResponse;
import org.simarro.alumno.helena.instituto.exception.DataValidationException;
import org.simarro.alumno.helena.instituto.exception.EntityAlreadyExistsException;
import org.simarro.alumno.helena.instituto.exception.EntityIlegalArgumentException;
import org.simarro.alumno.helena.instituto.exception.EntityNotFoundException;
import org.simarro.alumno.helena.instituto.helper.DataIntegrityViolationAnalyzer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final DataIntegrityViolationAnalyzer analyzer;

    public GlobalExceptionHandler(DataIntegrityViolationAnalyzer analyzer){
        this.analyzer = analyzer;

    }

    @ExceptionHandler(EntityIlegalArgumentException.class) // Datos incorrectos en la entidad que producirían
                                                           // inconsistencias
    public ResponseEntity<CustomErrorResponse> handleEntityIllegalArgumentException(EntityIlegalArgumentException ex) {
        CustomErrorResponse response = new CustomErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class) // No se ha encontrado la entidad con la clave primaria indicada
    public ResponseEntity<CustomErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        CustomErrorResponse response = new CustomErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class) // Se intenta crear una entidad con una clave primaria que ya
                                                          // existe
    public ResponseEntity<CustomErrorResponse> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        CustomErrorResponse response = new CustomErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataValidationException.class) // Validación incorrecta de un atributo
    public ResponseEntity<CustomErrorResponse> handleDataValidationException(DataValidationException ex) {
        CustomErrorResponse response = new CustomErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindingResultException.class) // Errores en las validaciones de BindingResult en la entidad
    public ResponseEntity<BindingResultErrorsResponse> handleBindingResultException(BindingResultException ex) {
        BindingResultErrorsResponse response = new BindingResultErrorsResponse(ex.getErrorCode(),
                ex.getValidationErrors());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // Error en las validaciones @Valid sobre la entidad cuando
                                                             // no hay BindingResult
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // devolver BAD_REQUEST con la lista de errores
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {
        // Extracto del mensaje subyacente de la excepción
        String detailedMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

        // Determinar el tipo de error (clave foránea, unicidad, etc.)
        String errorCode = analyzer.analyzeErrorCode(detailedMessage);
        String userMessage = analyzer.analyzeUserMessage(detailedMessage);

        // Crear la respuesta personalizada
        CustomErrorResponse response = new CustomErrorResponse(errorCode, userMessage);
        response.setDetailedMessage(detailedMessage); // Para depuración o registro interno

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)//Datos suministrados en la petición HTTP incorrectos
    public ResponseEntity<CustomErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        CustomErrorResponse response = new CustomErrorResponse("DATA_CONVERSION_ERROR","Error en el tipo de dato de uno de los atributos suministrados", ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}