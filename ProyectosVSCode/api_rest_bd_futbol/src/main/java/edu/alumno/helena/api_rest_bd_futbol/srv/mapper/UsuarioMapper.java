package edu.alumno.helena.api_rest_bd_futbol.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.alumno.helena.api_rest_bd_futbol.model.db.UsuarioDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.UsuarioInfo;

@Mapper(uses = RolMapper.class) //Para obtener List<RolInfo> en UsuarioInfo
public interface UsuarioMapper{
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
    @Mapping(target = "roles", source = "roles")
    UsuarioInfo usuarioDbToUsuarioInfo(UsuarioDb usuarioDb);
}