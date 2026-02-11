package edu.alumno.helena.api_rest_bd_pelicula.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.GeneroDb;

@Repository
public interface GeneroRepository extends JpaRepository<GeneroDb, Long> {
	Page<GeneroDb> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
