package org.simarro.alumno.helena.instituto.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CursoEdit {
    private Long id;
    @Size(min = 5, message = "El nombre del curso no puede estar vacio")
    private String nombre;
    @Size(min = 5, message = "La abreviatura no puede estar vacia")
    private String abreviatura;
    private String ciclo;
    private Integer tutor;
}
