package org.simarro.alumno.helena.instituto.responseHelpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private final String errorCode;

    public EntityNotFoundException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public EntityNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s con ID %d no encontrado", entityName, id));
        this.errorCode = "ENTITY_NOT_FOUND";
    }

    public String getErrorCode() {
        return errorCode;
    }
}
