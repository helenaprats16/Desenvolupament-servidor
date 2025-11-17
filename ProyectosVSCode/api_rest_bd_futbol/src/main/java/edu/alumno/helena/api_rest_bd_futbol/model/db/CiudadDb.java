package edu.alumno.helena.api_rest_bd_futbol.model.db;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import edu.alumno.helena.api_rest_bd_futbol.model.db.EquipoNombreDb;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "ciudades")
public class CiudadDb implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=4, message="El nombre debe de tener un tamaño minimo de 4 carácteres")
    private String nombre;
    @Column(nullable= true)
    private Long habitantes;
    //Definir los equipos con @OneToMany porque una ciudad puede tener muchos equipos:
        //mappedBy = "clave ajena". En EquipoDb la clave ajena es el atributo ciudadDb
    @OneToMany(mappedBy="ciudad")
    private Set<EquipoNombreDb> equiposNombreDb = new HashSet<>();

}