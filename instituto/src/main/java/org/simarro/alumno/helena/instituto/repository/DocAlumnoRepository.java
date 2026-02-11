package org.simarro.alumno.helena.instituto.repository;

import java.util.List;

import org.simarro.alumno.helena.instituto.model.db.DocAlumnoDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface DocAlumnoRepository extends JpaRepository<DocAlumnoDb, Long>, JpaSpecificationExecutor<DocAlumnoDb> {
    
    @Query("SELECT d.creador, COUNT(d) FROM DocAlumnoDb d GROUP BY d.creador")
    List<Object[]> countDocumentsGroupedByCreador();
}

