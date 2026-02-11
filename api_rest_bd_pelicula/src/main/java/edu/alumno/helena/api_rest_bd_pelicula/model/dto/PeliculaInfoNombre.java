package edu.alumno.helena.api_rest_bd_pelicula.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeliculaInfoNombre implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String titulo;
    private Integer año;
}
