package edu.alumno.helena.api_rest_bd_futbol.srv.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.alumno.helena.api_rest_bd_futbol.model.db.CiudadDb;
import edu.alumno.helena.api_rest_bd_futbol.model.db.EquipoDb;
import edu.alumno.helena.api_rest_bd_futbol.model.db.EquipoNombreDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.CiudadInfo;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EquipoInfoNombre;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EquipoList;

@Mapper
public interface EquipoMapper {
    EquipoMapper INSTANCE = Mappers.getMapper(EquipoMapper.class);


    @Mapping(target = "nombreCiudad", source="ciudadDb.nombre")
    EquipoList EquipoDbToEquipoList(EquipoDb equipoDb);
    
    
    List<EquipoList> equiposDbToEquiposList(List<EquipoDb> equiposDb);

    @Mapping(target = "nombre", source = "nombreLargo")
    EquipoInfoNombre equipoNombreDbToEquipoInfoNombre(EquipoNombreDb equipoNombreDb);
    Set<EquipoInfoNombre> equiposNombreDbToEquiposInfoNombre(Set<EquipoNombreDb> equiposDb);



}
