package edu.alumno.helena.api_rest_bd_pelicula.exception;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    // Resposta d'error comu per a tota la API
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String errorCode;
    private String message;
    private String path;
    // Errors de validacio per camp (opcional)
    private Map<String, String> fieldErrors;

    public ApiError() {
    }

   
}
