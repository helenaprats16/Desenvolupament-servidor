package edu.alumno.helena.api_rest_bd_pelicula.model.db;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor 
@Data 
@Entity 
@Table(name = "genero") 
public class GeneroDb implements Serializable{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @Size(min=5, message="El nombre debe de tener un tamaño minimo de 5 carácteres")
    private String nombre;
    @Column(nullable= true)
    private String descripcion;

    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY)
    private List<PeliculaDb> peliculas;
    
    
}