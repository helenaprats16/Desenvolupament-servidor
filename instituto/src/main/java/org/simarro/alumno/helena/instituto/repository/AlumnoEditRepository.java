package org.simarro.alumno.helena.instituto.repository;

import org.simarro.alumno.helena.instituto.model.db.AlumnoEditDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface AlumnoEditRepository extends JpaRepository<AlumnoEditDb, String>, JpaSpecificationExecutor<AlumnoEditDb> {
    
}
