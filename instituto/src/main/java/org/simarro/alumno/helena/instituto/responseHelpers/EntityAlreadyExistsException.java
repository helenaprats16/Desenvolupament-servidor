package org.simarro.alumno.helena.instituto.responseHelpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityAlreadyExistsException extends RuntimeException {
    
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
    
    public EntityAlreadyExistsException(String entityName, String field, Object value) {
        super(String.format("%s con %s '%s' ya existe", entityName, field, value));
    }
}