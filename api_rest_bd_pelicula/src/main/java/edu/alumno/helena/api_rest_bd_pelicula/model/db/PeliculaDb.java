package edu.alumno.helena.api_rest_bd_pelicula.model.db;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor 
@Data 
@Entity 
@Table(name = "pelicula") 
public class PeliculaDb implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 1, max = 150, message = "El título debe tener entre 1 y 150 caracteres")
    @Column(nullable = false)
    private String titulo;
    
    @NotNull(message = "El director es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    private DirectorDb director;
    
    @NotNull(message = "El género es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id", nullable = false)
    private GeneroDb genero;
    
    @NotNull(message = "El año es obligatorio")
    @Min(value = 1888, message = "El año debe ser posterior a 1888")
    @Max(value = 2100, message = "El año no puede ser mayor de 2100")
    @Column(name = "año", nullable = false)
    private Integer año;
    
    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración debe ser positiva")
    @Max(value = 1000, message = "La duración no puede superar 1000 minutos")
    @Column(name = "duracion_min", nullable = false)
    private Integer duracion;
    
    @Size(max = 1000, message = "La sinopsis no puede superar los 1000 caracteres")
    @Column(nullable = true)
    private String sinopsis;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    
}
