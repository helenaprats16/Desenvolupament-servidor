package org.simarro.alumno.helena.instituto.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.simarro.alumno.helena.instituto.model.db.AlumnoEditDb;
import org.simarro.alumno.helena.instituto.model.dto.AlumnoEdit;

@Mapper
public interface  AlumnoMapper {
    AlumnoMapper INSTANCE = Mappers.getMapper(AlumnoMapper.class);
    

    AlumnoEdit AlumnoEditDbToAlumnoEdit(AlumnoEditDb alumnoEditDb);
    AlumnoEditDb AlumnoEditToAlumnoEditDb(AlumnoEdit alumnoEdit);
    void updateAlumnoEditDbFromAlumnoEdit(AlumnoEdit alumnoEdit, @MappingTarget AlumnoEditDb alumnoEditDb);
    AlumnoEdit alumnoEditDbToAlumnoEdit(AlumnoEditDb save);
}
