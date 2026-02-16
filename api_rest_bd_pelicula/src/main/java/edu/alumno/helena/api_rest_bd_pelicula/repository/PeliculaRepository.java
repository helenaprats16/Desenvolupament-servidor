package edu.alumno.helena.api_rest_bd_pelicula.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.PeliculaDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculasPorAño;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculasPorGenero;
import io.micrometer.common.lang.NonNull;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaDb,Long>, JpaSpecificationExecutor<PeliculaDb> {

    @NonNull 
    List<PeliculaDb> findAll(@NonNull Sort sort);

    @NonNull 
    List<PeliculaDb> findPeliculaInfoById(Long id,@NonNull Sort sort);

    @NonNull
    List<PeliculaDb> findByDirectorId(Long directorId);

    @NonNull
    Page<PeliculaDb> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    @NonNull
    List<PeliculaDb> findByAño(Integer año);
    
    // Consulta agrupada: Películas por año con estadísticas
    @Query("SELECT new edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculasPorAño(" +
           "p.año, COUNT(p.id), AVG(p.duracion)) " +
           "FROM PeliculaDb p " +
           "GROUP BY p.año " +
           "ORDER BY p.año DESC")
    List<PeliculasPorAño> findPeliculasAgrupadasPorAño();
    
    // Consulta agrupada: Películas por género con estadísticas
    @Query("SELECT new edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculasPorGenero(" +
           "g.id, g.nombre, COUNT(p.id), AVG(p.duracion)) " +
           "FROM PeliculaDb p JOIN p.genero g " +
           "GROUP BY g.id, g.nombre " +
           "ORDER BY COUNT(p.id) DESC")
    List<PeliculasPorGenero> findPeliculasAgrupadasPorGenero();

}
