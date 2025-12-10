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
    
    @Size(min = 1, message = "El título debe de tener un tamaño mínimo de 1 carácter")
    @Column(nullable = false)
    private String titulo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    private DirectorDb director;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id", nullable = false)
    private GeneroDb genero;
    
    @Column(name = "año", nullable = false)
    private Integer año;
    
    @Column(name = "duracion_min", nullable = false)
    private Integer duracion;
    
    @Column(nullable = true)
    private String sinopsis;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    
}
