package edu.alumno.helena.api_rest_bd_futbol.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorList implements Serializable{
    @Size(min=3, message="El id del equipo tiene un tamaño mínimo de 3")
    private String idEquipo;
    private Long dorsal;
    @Size(min=10,max=30,message="El nombre debe de tener un tamaño entre 10 y 30 caracters")
    private String nombre;
}