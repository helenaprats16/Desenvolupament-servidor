package org.simarro.alumno.helena.instituto.model.db;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="matriculaciones")
public class MatriculaEditDb {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String alumno;
    private Integer modulo;
    @NotNull(message= "La nota no puede estar vacia")
    @Range(min = 0, max= 10, message = "La nota debe ser igual o mayor a 0 y menor que 10")
    private String nota;
}
