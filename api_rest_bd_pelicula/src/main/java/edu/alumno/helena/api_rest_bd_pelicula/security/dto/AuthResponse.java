package edu.alumno.helena.api_rest_bd_pelicula.security.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;

    
}
