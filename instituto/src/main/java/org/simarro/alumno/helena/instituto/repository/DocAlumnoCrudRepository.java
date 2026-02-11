package org.simarro.alumno.helena.instituto.repository;
import org.simarro.alumno.helena.instituto.model.db.DocAlumnoEditDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocAlumnoCrudRepository extends JpaRepository<DocAlumnoEditDb, Long> {
}
