package edu.alumno.helena.api_rest_bd_pelicula.helper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FiltroException extends RuntimeException {

    private final String errorCode;

    // Constructor simple
    public FiltroException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    // Constructor amd detall adicional
    public FiltroException(String errorCode, String message, String details) {
        super(message + (details != null ? " - " + details : ""));
        this.errorCode = errorCode;
    }

    // Constructor amb la causa
    public FiltroException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
