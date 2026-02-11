package org.simarro.alumno.helena.instituto.responseHelpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityIllegalArgumentException extends RuntimeException {
    
    public EntityIllegalArgumentException(String message) {
        super(message);
    }
    
    public EntityIllegalArgumentException(String field, Object value, String expectedType) {
        super(String.format("Valor '%s' no válido para el campo '%s'. Se esperaba: %s", 
                           value, field, expectedType));
    }
    
    public EntityIllegalArgumentException(String entityName, String operation, String reason) {
        super(String.format("No se puede %s %s: %s", operation, entityName, reason));
    }
}