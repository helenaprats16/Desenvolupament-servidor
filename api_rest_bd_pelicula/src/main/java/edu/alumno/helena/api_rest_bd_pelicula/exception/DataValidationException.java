package edu.alumno.helena.api_rest_bd_pelicula.exception;

import lombok.Getter;

@Getter
public class DataValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String message;

    public DataValidationException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
