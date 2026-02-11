package edu.alumno.helena.api_rest_bd_pelicula.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeliculaCreate implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "El titulo no puede estar vacio")
    @Size(min = 1, max = 150, message = "El titulo debe tener entre 1 y 150 caracteres")
    private String titulo;

    @NotNull(message = "El director es obligatorio")
    private Long directorId;

    @NotNull(message = "El genero es obligatorio")
    private Long generoId;

    @NotNull(message = "El anio es obligatorio")
    @Min(value = 1888, message = "El anio debe ser posterior a 1888")
    @Max(value = 2100, message = "El anio no puede ser mayor de 2100")
    private Integer año;

    @NotNull(message = "La duracion es obligatoria")
    @Min(value = 1, message = "La duracion debe ser positiva")
    @Max(value = 1000, message = "La duracion no puede superar 1000 minutos")
    private Integer duracion;

    @Size(max = 1000, message = "La sinopsis no puede superar los 1000 caracteres")
    private String sinopsis;
}
