package edu.alumno.helena.api_rest_bd_futbol.model.db;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@IdClass(JugadorId.class)
@Table(name="jugadores")
public class JugadorDb implements Serializable{

    private static final long serialVersionUID = -818542778373595260L;
    @Id
    @Size(min=3,message="El id del equipo tiene un tamaño mínimo de 3")
    private String idEquipo;
    @Id
    @Column(nullable = false)
    private Long dorsal;
    @Size(min=10, max=30,message="EL nombre debe tener un tamaño entre 10 y 30 caracters")
    private String nombre;
    @Size(max=10,message="La posicion debe de tener un tamaño ,áximo de 30 caracters")
    private String posicion;
    private Long sueldo;



}