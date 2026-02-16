package edu.alumno.helena.api_rest_bd_pelicula.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Estadísticas de directores con número de películas")
public class DirectorEstadistica {
    
    @Schema(description = "ID del director", example = "1")
    private Long directorId;
    
    @Schema(description = "Nombre del director", example = "Christopher Nolan")
    private String nombre;
    
    @Schema(description = "Nacionalidad del director", example = "Británico")
    private String nacionalidad;
    
    @Schema(description = "Número total de películas", example = "5")
    private Long totalPeliculas;
}
