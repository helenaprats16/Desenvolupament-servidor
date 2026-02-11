package org.simarro.alumno.helena.instituto.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.simarro.alumno.helena.instituto.model.db.CursoEditDb;
import org.simarro.alumno.helena.instituto.model.dto.CursoEdit;

@Mapper
public interface  CursoMapper {
    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);
    

    CursoEdit CursoEditDbToCursoEdit(CursoEditDb cursoEditDb);
    CursoEditDb CursoEditToCursoEditDb(CursoEdit cursoEdit);
    void updateCursoEditDbFromCursoEdit(CursoEdit cursoEdit, @MappingTarget CursoEditDb cursoEditDb);
}
