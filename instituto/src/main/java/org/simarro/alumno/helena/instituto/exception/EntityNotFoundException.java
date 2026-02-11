package org.simarro.alumno.helena.instituto.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
    private final String errorCode;

    public EntityNotFoundException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
