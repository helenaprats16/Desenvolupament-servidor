package edu.alumno.helena.api_rest_bd_futbol.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JugadorDTO {
    private String nombre;
    private Integer dorsal;
    private Long sueldo;
}
