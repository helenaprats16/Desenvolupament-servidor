package edu.alumno.helena.dwes_futbol_rest.srv.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import edu.alumno.helena.dwes_futbol_rest.model.db.CiudadDb;
import edu.alumno.helena.dwes_futbol_rest.model.dto.CiudadInfo;
import edu.alumno.helena.dwes_futbol_rest.model.db.CiudadDb;
import edu.alumno.helena.dwes_futbol_rest.model.dto.CiudadInfo;
import edu.alumno.helena.dwes_futbol_rest.model.dto.CiudadList;

@Mapper
public interface CiudadMapper {
    CiudadMapper INSTANCE = Mappers.getMapper(CiudadMapper.class);

    CiudadInfo CiudadDbToCiudadInfo(CiudadDb ciudadDb);
    CiudadList CiudadDbToCiudadList(CiudadDb ciudadDb);
    List<CiudadList> ciudadesToCiudadList(List<CiudadDb> ciudadesDb);
}
