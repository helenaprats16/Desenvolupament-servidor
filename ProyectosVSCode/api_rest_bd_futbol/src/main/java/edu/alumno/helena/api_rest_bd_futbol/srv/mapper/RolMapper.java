package edu.alumno.helena.api_rest_bd_futbol.srv.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import edu.alumno.helena.api_rest_bd_futbol.model.db.RolDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.RolInfo;

@Mapper
public interface RolMapper{
    RolMapper INSTANCE = Mappers.getMapper(RolMapper.class);

    //Metodo para mapear un RolDb a un RolInfo
    RolInfo rolDbTRolInfo(RolDb rolDb);

    //Metodos para mapear una lista de RolDb a RolInfo
    Set<RolInfo> rolesDbToRolInfo(Set<RolDb> rolesDb);
}