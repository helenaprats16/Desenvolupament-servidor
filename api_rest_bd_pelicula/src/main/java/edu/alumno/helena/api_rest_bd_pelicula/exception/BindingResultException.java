package edu.alumno.helena.api_rest_bd_pelicula.exception;

import java.util.Map;

import lombok.Getter;

@Getter
public class BindingResultException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private Map<String, String> validationErrors;

    public BindingResultException(String errorCode, Map<String, String> validationErrors) {
        this.errorCode = errorCode;
        this.validationErrors = validationErrors;
    }
}
