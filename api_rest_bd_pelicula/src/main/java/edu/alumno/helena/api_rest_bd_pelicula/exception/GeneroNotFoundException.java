package edu.alumno.helena.api_rest_bd_pelicula.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GeneroNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String message;
}
