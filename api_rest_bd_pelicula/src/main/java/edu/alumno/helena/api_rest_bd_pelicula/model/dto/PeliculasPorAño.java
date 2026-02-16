package edu.alumno.helena.api_rest_bd_pelicula.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Películas agrupadas por año de estreno")
public class PeliculasPorAño {
    
    @Schema(description = "Año de estreno", example = "2010")
    private Integer año;
    
    @Schema(description = "Cantidad de películas estrenadas ese año", example = "15")
    private Long cantidad;
    
    @Schema(description = "Duración promedio en minutos", example = "125")
    private Double duracionPromedio;
}
