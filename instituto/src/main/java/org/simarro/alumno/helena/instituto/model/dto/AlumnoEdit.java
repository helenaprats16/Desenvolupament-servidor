package org.simarro.alumno.helena.instituto.model.dto;

import org.hibernate.validator.constraints.Range;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class AlumnoEdit {
  
    @Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El dni debe tener 8 números y una letra")
    @Schema(example = "12345678A", description = "DNI formado por 8 números y una letra")
    
    private String dni;
    @Size(min = 5, message = "El nombre debe de tener un tamaño mínimo de 5 carácteres")
    @Size(max = 255, message = "El nombre debe de tener un tamaño máximo de 255 carácteres")
    @Schema(example = "Nombre Apellido1 Apellido2", description = "Nombre entre 5 y 255 caracteres")
    private String nombre;
    @NotNull(message = "La edad no puede estar vacia")
    @Range(min = 18, max = 99, message = "La edad debe ser igual o mayor a 18 y menor o igual a 99")
    @Digits(integer = 2, fraction = 0, message = "La edad no puede tener decimales ni más de 2 dígitos")
    @Schema(example = "18", description = "Edad del alumno")
    private Integer edad;
    @Schema(example = "2", description = "Clave ajena al curso en el que esta matriculado")
    private Long curso;
    @Schema(example = "false", description = "Alumno en erasmus (true/false)")
    private Boolean erasmus = false;
    @Size(max = 255, message = "El lenguaje favorito debe de tener un tamaño máximo de 255 carácteres")
    @Schema(example = "Spring boot", description = "Lenguajes y frameworks favoritos")
    private String lenguajeFavorito = "";
    @Size(max = 255, message = "El genero debe de tener un tamaño máximo de 255 carácteres")
    @Schema(example = "Mujer", description = "Genero del alumno")
    private String genero;
    @Size(max = 255, message = "El horario debe de tener un tamaño máximo de 255 carácteres")
    @Schema(example = "Tarde", description = "Horario principal del alumno (Mañana/Tarde)")
    private String horario;
    @Size(max = 255, message = "El pais debe de tener un tamaño máximo de 255 carácteres")
    @Schema(example = "España", description = "Pais del alumno")
    private String pais;
    @Size(max = 255, message = "Los hobbies debe de tener un tamaño máximo de 255 carácteres")
    @Schema(example = "Programar", description = "Hobbies del alumno")
    private String hobbies;
    

}