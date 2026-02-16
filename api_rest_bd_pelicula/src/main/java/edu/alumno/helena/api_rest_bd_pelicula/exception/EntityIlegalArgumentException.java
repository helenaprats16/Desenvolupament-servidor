package edu.alumno.helena.api_rest_bd_pelicula.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityIlegalArgumentException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String message;

    public EntityIlegalArgumentException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
