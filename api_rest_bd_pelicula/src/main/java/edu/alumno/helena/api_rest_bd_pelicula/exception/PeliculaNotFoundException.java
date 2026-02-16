package edu.alumno.helena.api_rest_bd_pelicula.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeliculaNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String message;

    public PeliculaNotFoundException() {
        super();
    }

    public PeliculaNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
