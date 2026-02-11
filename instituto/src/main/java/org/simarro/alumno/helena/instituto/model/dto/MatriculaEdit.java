package org.simarro.alumno.helena.instituto.model.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MatriculaEdit {
    private Long id;
    private String alumno;
    private Integer modulo;
    @NotNull(message= "La nota no puede estar vacia")
    @Range(min = 0, max= 10, message = "La nota debe ser igual o mayor a 0 y menor que 10")
    private String nota;
}
