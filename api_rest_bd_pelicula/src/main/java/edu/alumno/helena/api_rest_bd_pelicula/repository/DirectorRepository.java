package edu.alumno.helena.api_rest_bd_pelicula.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.DirectorDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorEstadistica;
import io.micrometer.common.lang.NonNull;

@Repository
public interface DirectorRepository extends JpaRepository<DirectorDb,Long>, JpaSpecificationExecutor<DirectorDb> {

    @NonNull 
    List<DirectorDb> findAll(@NonNull Sort sort);
    @NonNull 
    List<DirectorDb> findDirectorInfoById(Long id,@NonNull Sort sort);

    Page<DirectorDb> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    
    // Consulta agrupada: Estadísticas de directores con conteo de películas
    @Query("SELECT new edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorEstadistica(" +
           "d.id, d.nombre, d.nacionalidad, COUNT(p.id)) " +
           "FROM DirectorDb d LEFT JOIN d.peliculasInfoDb p " +
           "GROUP BY d.id, d.nombre, d.nacionalidad " +
           "ORDER BY COUNT(p.id) DESC")
    List<DirectorEstadistica> findDirectoresConEstadisticas();
    
    // Consulta agrupada: Directores con más de X películas
    @Query("SELECT new edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorEstadistica(" +
           "d.id, d.nombre, d.nacionalidad, COUNT(p.id)) " +
           "FROM DirectorDb d LEFT JOIN d.peliculasInfoDb p " +
           "GROUP BY d.id, d.nombre, d.nacionalidad " +
           "HAVING COUNT(p.id) >= :minPeliculas " +
           "ORDER BY COUNT(p.id) DESC")
    List<DirectorEstadistica> findDirectoresConMinimoPeliculas(Long minPeliculas);
}
