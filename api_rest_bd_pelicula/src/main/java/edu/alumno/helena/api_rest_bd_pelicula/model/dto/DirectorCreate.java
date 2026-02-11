package edu.alumno.helena.api_rest_bd_pelicula.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DirectorCreate implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "El nombre no puede ser vacio")
    @Size(min = 4, max = 100, message = "El nombre debe tener entre 4 y 100 caracteres")
    private String nombre;

    @Size(max = 50, message = "La nacionalidad no puede superar 50 caracteres")
    private String nacionalidad;
}
