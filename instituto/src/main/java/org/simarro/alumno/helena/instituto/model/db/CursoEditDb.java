package org.simarro.alumno.helena.instituto.model.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="cursos")
public class CursoEditDb {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Size(min = 5, message = "El nombre debe de tener un tamaño mínimo de 5 caracters")
    @Column(nullable = false)
    private String nombre;
    private String abreviatura;
    private String ciclo;
    private Integer tutor;
}
