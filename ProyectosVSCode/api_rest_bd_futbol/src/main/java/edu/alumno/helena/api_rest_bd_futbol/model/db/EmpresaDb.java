package edu.alumno.helena.api_rest_bd_futbol.model.db;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
@Table(name = "ex_empresas") 
public class EmpresaDb implements Serializable{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @Size(min=4, message="El nombre debe de tener un tamaño minimo de 4 carácteres")
    private String nombre;
    private String pais;
    private Integer fundacion;
    private boolean activa;
    @Column(name="sitio_web")
    private String sitioWeb;



    //Una empresa puede desarrollar múltiples videojuegos
    //@OneToMany(mappedBy="videojuegos") 
    //private Set<EquipoNombreDb> equiposNombreDb = new HashSet<>();

}
