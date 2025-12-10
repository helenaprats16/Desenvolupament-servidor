package edu.alumno.helena.api_rest_bd_pelicula.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeliculaList implements Serializable{
    private static final long serialVersionUID=1L;
     private Long id;
    private String titulo;
    private Long directorId;
    private String directorNombre;
    private Long generoId;
    private String generoNombre;
    private Integer año;
    private Integer duracion;
    private String sinopsis;
}