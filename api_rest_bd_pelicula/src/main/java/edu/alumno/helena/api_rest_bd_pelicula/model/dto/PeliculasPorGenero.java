package edu.alumno.helena.api_rest_bd_pelicula.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Películas agrupadas por género")
public class PeliculasPorGenero {
    
    @Schema(description = "ID del género", example = "1")
    private Long generoId;
    
    @Schema(description = "Nombre del género", example = "Acción")
    private String genero;
    
    @Schema(description = "Cantidad de películas de este género", example = "25")
    private Long cantidad;
    
    @Schema(description = "Duración promedio en minutos", example = "130")
    private Double duracionPromedio;
}
