package edu.alumno.helena.api_rest_bd_futbol.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {
    private static final long serialVersionUID =1L;
    private String message;
    private String errorCode;
}