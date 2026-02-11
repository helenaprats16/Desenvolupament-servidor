package edu.alumno.helena.api_rest_bd_futbol.model.db;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import edu.alumno.helena.api_rest_bd_futbol.model.db.JugadorDb;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@IdClass(JugadorId.class)
@Table(name="porteros")
public class PorteroDb implements Serializable{

    private static final long serialVersionUID = -818542778373595260L;
    @Id
    @Size(min=3,message="El id del equipo tiene un tamaño mínimo de 3")
    private String idEquipo;
    @Id
    @Column(nullable = false)
    private Long dorsal;
    private String partidos;
    private String goles;
    @OneToOne
    @JoinColumn(name="idEquipo", referencedColumnName="idequipo")
    @JoinColumn(name="dorsal", referencedColumnName="dorsal")
    private JugadorDb jugadorDb;

}