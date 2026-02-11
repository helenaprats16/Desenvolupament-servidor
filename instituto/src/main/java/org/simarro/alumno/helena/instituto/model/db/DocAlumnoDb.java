package org.simarro.alumno.helena.instituto.model.db;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

@Table(name = "docalumnos")
public class DocAlumnoDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Long id;
    @Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El dni debe tener 8 números y una letra")
    @Column(nullable = false)
    private String dni;
    @Size(min = 5, message = "El nombre debe de tener un tamaño mínimo de 5 carácteres")
    @Size(max = 255, message = "El nombre debe de tener un tamaño máximo de 255 carácteres")
    @Column(name = "nombre_fichero", nullable = false, length = 255)
    private String nombreFichero;
    private String comentario;
    @NotNull(message = "Debe indicarse la persona que ha creado el fichero")
    @Column(name = "creado_por", nullable = false, length = 255)
    private String creador;

}

