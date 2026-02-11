package org.simarro.alumno.helena.instituto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityAlreadyExistsException extends RuntimeException {
    
    private final String errorCode;

    public EntityAlreadyExistsException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }


}

   