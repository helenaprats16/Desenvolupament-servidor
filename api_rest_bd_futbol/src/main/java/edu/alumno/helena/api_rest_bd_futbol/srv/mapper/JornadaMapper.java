package edu.alumno.helena.api_rest_bd_futbol.srv.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import edu.alumno.helena.api_rest_bd_futbol.model.db.JornadaDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.JornadaInfo;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.JornadaList;

@Mapper
public interface JornadaMapper {
    JornadaMapper INSTANCE = Mappers.getMapper(JornadaMapper.class);

    JornadaInfo JornadaDbToJornadaInfo(JornadaDb jornadaDb);
    JornadaList JornadaDbToJornadaList(JornadaDb jornadaDb);
    List<JornadaList> JornadaToJornadaList(List<JornadaDb> jornadaDbs);


}
