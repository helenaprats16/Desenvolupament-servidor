package edu.alumno.helena.api_rest_bd_pelicula.srv.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.GeneroDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroInfo;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    GeneroInfo generoDbToGeneroInfo(GeneroDb generoDb);

    List<GeneroInfo> generosToGeneroInfo(List<GeneroDb> generos);

    GeneroDb generoCreateToGeneroDb(GeneroCreate generoCreate);
}
