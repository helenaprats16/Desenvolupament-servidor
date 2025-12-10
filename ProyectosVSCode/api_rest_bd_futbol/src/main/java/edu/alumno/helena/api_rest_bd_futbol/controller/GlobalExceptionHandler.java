package edu.alumno.helena.api_rest_bd_futbol.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.alumno.helena.api_rest_bd_futbol.exception.CiudadNotFoundException;
import edu.alumno.helena.api_rest_bd_futbol.exception.CustomErrorResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler(CiudadNotFoundException.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // public ResponseEntity<CustomErrorResponse> handleCiudadNotFoundExcepcion(CiudadNotFoundException ex){
    //     CustomErrorResponse response = new CustomErrorResponse(ex.getErrorCode(), ex.getMessage());
    //     return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    // }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){
        return new ResponseEntity<>("Interval server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //per tractar el errors quan fas un POST que i no guardes be o el nickname o el password
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){//añadimos un mensaje por cada error
            errors.put(error.getField(), error.getDefaultMessage());
        
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
        
    }


}