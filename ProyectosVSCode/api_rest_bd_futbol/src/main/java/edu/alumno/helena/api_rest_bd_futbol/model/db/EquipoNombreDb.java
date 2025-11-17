package edu.alumno.helena.api_rest_bd_futbol.model.db;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="equipos")
public class EquipoNombreDb implements Serializable{

    private static final long serialVersionUID = -818542778373595260L;
    @Id
    @Size(min=3, message="El id tiene un tamaño mínimo de 3")
    private String id;
    @Size(min=10,max=40,message="El nombre largo debe de tener un tamaño minimo de 10")
    @Column(name="`nombrelargo`")
    private String nombreLargo;
    private Long ciudad;

}