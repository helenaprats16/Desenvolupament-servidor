package edu.alumno.helena.api_rest_bd_pelicula.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.PeliculaDb;
import io.micrometer.common.lang.NonNull;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaDb,Long> {

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

}
