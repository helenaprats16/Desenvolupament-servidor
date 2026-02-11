package org.simarro.alumno.helena.instituto.repository;

import org.simarro.alumno.helena.instituto.model.db.ProfesorEditDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ProfesorEditRepository extends JpaRepository<ProfesorEditDb, Long>, JpaSpecificationExecutor<ProfesorEditDb> {
    
}