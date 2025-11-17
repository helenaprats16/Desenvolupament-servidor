package edu.alumno.helena.api_rest_bd_futbol.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipoInfoNombre{
    @Size(min=3,message="El id tiene un tamaño mínimo de 3")
    private String id;
    @Size(min=10,max=40,message="El nombre largo debe de tener un tamaño entre 10 y 40 caracteres")
    private String nombre;
}