package org.simarro.alumno.helena.instituto.responseHelpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataValidationException extends RuntimeException {
    
    public DataValidationException(String message) {
        super(message);
    }
    
    public DataValidationException(String field, String error) {
        super(String.format("Error de validación en campo '%s': %s", field, error));
    }
}