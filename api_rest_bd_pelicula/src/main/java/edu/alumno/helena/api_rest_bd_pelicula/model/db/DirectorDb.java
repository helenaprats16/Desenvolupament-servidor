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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor 
@Data 
@Entity 
@Table(name = "director") 
public class DirectorDb implements Serializable{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @NotBlank(message="El nombre no puede ser vacío")
    @Size(min = 4, max = 100, message="El nombre debe tener entre 4 y 100 caracteres")
    private String nombre;
    @Column(nullable= true)
    private String nacionalidad;
    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY)
    private List<PeliculaDb> peliculasInfoDb;



}
