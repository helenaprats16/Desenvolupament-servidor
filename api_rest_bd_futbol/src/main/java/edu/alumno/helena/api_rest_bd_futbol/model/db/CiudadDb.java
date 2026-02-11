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

@NoArgsConstructor //Genera automáticamente un constructor sin parámetros
@AllArgsConstructor //Genera automáticamente un constructor con todos los parámetros
@Data //Combina: @Getter, @Setter, @ToString. @EqualsAndHashCode, @RequiredArgsConstructor
@Entity //Indica que esta clase es una entidad JPA = La clase es una tabla de la bbdds
@Table(name = "ciudades") //Ponerle el nombre a la tabla [és Opcional però millor posar-ho sempre per no tindrer mal entessos]
public class CiudadDb implements Serializable{

    @Id // Marca este campo como la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) //La base de datos generará automaticamente el valor  (auto-increment)
    private Long id;
    @Size(min=4, message="El nombre debe de tener un tamaño minimo de 4 carácteres")
    private String nombre;
    @Column(nullable= true)// permite valores nulos en la columnda de la base de datos
    private Long habitantes;
    //Definir los equipos con @OneToMany porque una ciudad puede tener muchos equipos:
    @OneToMany(mappedBy="ciudad") //Define una relación uno a muchos (una ciudad muchos equipos)
    //mappedBy="clave ajena": Indica que la relación es bidireccional y el dueño de la relación es el campo ciudad en la clase EquipoNombreDb
    private Set<EquipoNombreDb> equiposNombreDb = new HashSet<>();

}

