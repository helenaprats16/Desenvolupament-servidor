package edu.alumno.helena.api_rest_bd_futbol.model.db;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "equipos")
public class EquipoDb implements Serializable{

    private static final long serialVersionUID = -8185427783735955260L;
    @Id
    @Size(min=3, message="El id tiene un tamaño mínimo de 3")
    private String id;
    @Size(min=4, max=20,message="El nombre corto debe de tener un tamaño entre 3 y 20")
    @Column(name="`nombrecorto`")
    private String nombreCorto;
    @Size(min=10, max=40,message="El nombre largo debe de tener un tamaño entre 10 y 40 caracteres")
    @Column(name="`nombrelargo`")
    private String nombreLargo;
    @ManyToOne
    @JoinColumn(name="ciudad") //define la foreign key [se refiere literalmente al nombre de la columna en la base de datos]
    private CiudadDb ciudadDb;
    @Size(min=10,max=30, message="El nombre del entrenador largo debe de tener un tamaño entre 10 y 30 caracteres")
    private String entrenador;
    @Size(min=10,max=30, message="El nombre del estadio largo debe de tener un tamaño entre 10 y 30 caracteres")
    private String estadio;
    @Size(min=10,max=30, message="El nombre de la marca debe de tener un tamaño entre 4 y 30 caracteres")
    private String marca;
    @Size(min=4,max=30, message="El nombre del patrocinador tener un tamaño entre 4 y 30 caracteres")
    private String patrocinador;
    private Long presupuesto;


}