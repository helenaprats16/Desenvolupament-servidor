package edu.alumno.helena.api_rest_bd_futbol.srv.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import edu.alumno.helena.api_rest_bd_futbol.model.db.JugadorDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.JugadorList;
@Mapper
public interface JugadorMapper{
    JugadorMapper INSTANCE = Mappers.getMapper(JugadorMapper.class);

    List<JugadorList> jugadoresDbtoJugadoresList(List<JugadorDb> jugadoresDb);
}