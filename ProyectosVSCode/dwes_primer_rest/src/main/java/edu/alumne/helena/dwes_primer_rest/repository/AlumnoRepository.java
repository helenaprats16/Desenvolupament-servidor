package edu.alumne.helena.dwes_primer_rest.repository;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.alumne.helena.dwes_primer_rest.model.db.AlumnoDb;

@Repository
public interface AlumnoRepository extends JpaRepository<AlumnoDb, String> {
    // Por defecto se utiliza Jakarta Persistence Query Language (JPQL)
    // Utilizamos el nombre de la clase "AlumnoDb"
    @Query("SELECT a FROM AlumnoDb a WHERE a.ciclo='DAW'")
    Collection<AlumnoDb> findAllAlumnoDbDAW();

    // Podemos utilizar SQL nativo (native SQL)
    // Utilizamos el nombre de la tabla 'alumnos' en la BD
    @Query(value = "SELECT * FROM alumnos as a WHERE a.ciclo='DAM'", nativeQuery = true)
    Collection<AlumnoDb> findAllAlumnoDbDAM();

    // 🔹 CONSULTAS CON PARÁMETROS 🔹

    // JPQL
    @Query("SELECT a FROM AlumnoDb a WHERE a.ciclo=:ciclo")
    Collection<AlumnoDb> findAllAlumnoDbCiclo(@Param("ciclo") String ciclo);

    // Native SQL
    @Query(value = "SELECT * FROM alumnos a WHERE a.ciclo=:ciclo AND a.pais=:pais", nativeQuery = true)
    Collection<AlumnoDb> findAllAlumnoDbCicloPais(@Param("ciclo") String ciclo,
            @Param("pais") String pais);

    // Métodos automáticos en Spring Data JPA
    Collection<AlumnoDb> findByDni(String dni);

    Collection<AlumnoDb> findByCiclo(String ciclo);

    Collection<AlumnoDb> findByHorario(String horario);

    Collection<AlumnoDb> findByEdad(Integer edad);

    Collection<AlumnoDb> findByCicloAndPais(String ciclo, String pais);

    Collection<AlumnoDb> findDistinctByNombre(String nombre);

    Collection<AlumnoDb> findByNombreIgnoreCase(String nombre);

    Collection<AlumnoDb> findByCicloAndPaisAllIgnoreCase(String ciclo, String pais);

    Collection<AlumnoDb> findByNombreLike(String nombre);

    // Ordenación de resultados
    @Query("SELECT a FROM AlumnoDb a WHERE a.ciclo=:ciclo ORDER BY a.curso ASC")
    Collection<AlumnoDb> findAllAlumnoDbCicloOrderByCurso(@Param("ciclo") String ciclo);

    // Consultas con varias ordenaciones en Sptring Data JPA
    @Query("SELECT a FROM AlumnoDb a WHERE a.ciclo=:ciclo")
    Collection<AlumnoDb> findByCicloOrderBy(String ciclo, Sort sort);
    @Query("SELECT a FROM AlumnoDb a WHERE a.edad=:edad")
    Collection<AlumnoDb> findByEdad(String edad, Sort sort);
     // Metodos automaticos en Spring Data JPA
    Collection<AlumnoDb> findByHorario(String horario, Sort sort);


}