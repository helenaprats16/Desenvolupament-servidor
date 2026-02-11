package org.simarro.alumno.helena.instituto.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.simarro.alumno.helena.instituto.model.db.ModuloEditDb;
import org.simarro.alumno.helena.instituto.model.dto.ModuloEdit;

@Mapper
public interface ModuloMapper {
    ModuloMapper INSTANCE = Mappers.getMapper(ModuloMapper.class);
    

    //Devuelve un objeto de tipo "ModuloEdit" a partir de un objeto de tipo "ModuloEditDb"
    ModuloEdit ModuloEditDbToModuloEdit(ModuloEditDb moduloEditDb);
    //Devuelve un objeto de tipo "ModuloEditDb" a partir de un objeto de tipo "ModuloEdit"
    ModuloEditDb ModuloEditToModuloEditDb(ModuloEdit moduloEdit);
    //Actualiza un objeto de tipo "ModuloEditDb" con los datos de un objeto de tipo "ModuloEdit"
    void updateModuloEditDbFromModuloEdit(ModuloEdit moduloEdit, @MappingTarget ModuloEditDb moduloEditDb);
}
