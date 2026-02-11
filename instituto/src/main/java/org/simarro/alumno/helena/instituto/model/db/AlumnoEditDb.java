package org.simarro.alumno.helena.instituto.model.db;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="alumnos")
public class AlumnoEditDb {
    @Id
    @Pattern(regexp="[0-9]{8}[A-Za-z]{1}", message = "El dni debe tener 8 números")
    private String dni;
    @Size(min = 5, message = "El nombre debe de tener un tamaño mínimo de 5 caracters")
    private String nombre;
    @NotNull(message= "La edad no puede estar vacia")
    @Range(min = 18, max= 99, message = "La edad debe ser igual o mayor a 18 y menor que 99")
    @Digits(integer = 2, fraction=0, message="La edad no puede tener decimales")
    private Integer edad;
    private Long curso;
    private boolean erasmus= false;
    private String lenguajeFavorito="";
    private String genero;
    private String horario;
    private String pais;
    private String hobbies;
    

}
