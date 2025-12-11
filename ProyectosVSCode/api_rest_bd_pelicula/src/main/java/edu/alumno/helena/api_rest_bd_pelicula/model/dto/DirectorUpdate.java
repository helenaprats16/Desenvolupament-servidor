package edu.alumno.helena.api_rest_bd_pelicula.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data 
public class DirectorUpdate implements Serializable{
    private String nombre;
    private String nacionalidad;

}