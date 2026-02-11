package edu.alumno.helena.api_rest_bd_pelicula.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.DirectorDb;
import io.micrometer.common.lang.NonNull;

@Repository
public interface DirectorRepository extends JpaRepository<DirectorDb,Long> {

    @NonNull 
    List<DirectorDb> findAll(@NonNull Sort sort);
    @NonNull 
    List<DirectorDb> findDirectorInfoById(Long id,@NonNull Sort sort);

    Page<DirectorDb> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
