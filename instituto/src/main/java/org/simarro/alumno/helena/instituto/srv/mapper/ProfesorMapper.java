package org.simarro.alumno.helena.instituto.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.simarro.alumno.helena.instituto.model.db.ProfesorEditDb;
import org.simarro.alumno.helena.instituto.model.dto.ProfesorEdit;

@Mapper
public interface  ProfesorMapper {
    ProfesorMapper INSTANCE = Mappers.getMapper(ProfesorMapper.class);
    

    ProfesorEdit ProfesorEditDbToProfesorEdit(ProfesorEditDb profesorEditDb);
    ProfesorEditDb ProfesorEditToProfesorEditDb(ProfesorEdit profesorEdit);
    void updateProfesorEditDbFromProfesorEdit(ProfesorEdit profesorEdit, @MappingTarget ProfesorEditDb profesorEditDb);
}

