package edu.alumno.helena.api_rest_bd_pelicula.model.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectorList implements Serializable{
    private static final long serialVersionUID=1L;
    private Long id;
    private String nombre;
    private String nacionalidad;
    private Set<PeliculaInfoNombre> peliculasInfoNombres = new HashSet<>();

}