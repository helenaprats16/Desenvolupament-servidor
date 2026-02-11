package org.simarro.alumno.helena.instituto.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.simarro.alumno.helena.instituto.model.db.MatriculaEditDb;
import org.simarro.alumno.helena.instituto.model.dto.MatriculaEdit;

@Mapper
public interface  MatriculaMapper {
    MatriculaMapper INSTANCE = Mappers.getMapper(MatriculaMapper.class);
    

    MatriculaEdit MatriculaEditDbToMatriculaEdit(MatriculaEditDb matriuclaEditDb);
    MatriculaEditDb MatriculaEditToMatriculaEditDb(MatriculaEdit matriculaEdit);
    void updateMatriculaEditDbFromMatriculaEdit(MatriculaEdit matriculaEdit, @MappingTarget MatriculaEditDb matriculaEditDb);
}
