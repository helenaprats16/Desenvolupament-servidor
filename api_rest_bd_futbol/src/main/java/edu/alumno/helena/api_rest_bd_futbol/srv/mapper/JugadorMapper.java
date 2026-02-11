package edu.alumno.helena.api_rest_bd_futbol.srv.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import edu.alumno.helena.api_rest_bd_futbol.model.db.JugadorDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.FiltroBusqueda;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.JugadorList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaResponse;

@Mapper
public interface JugadorMapper {
    JugadorMapper INSTANCE = Mappers.getMapper(JugadorMapper.class);

    /**
     * Convierte una lista de entidades JugadorDb en DTOs JugadorList
     */
    List<JugadorList> jugadoresDbToJugadoresList(List<JugadorDb> jugadoresDb);

    /**
     * Convierte una página de jugadores en una respuesta paginada
     */
    static PaginaResponse<JugadorList> pageToPaginaResponse(
            Page<JugadorDb> page,
            List<FiltroBusqueda> filtros,
            List<String> ordenaciones) {
        return new PaginaResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                JugadorMapper.INSTANCE.jugadoresDbToJugadoresList(page.getContent()),
                filtros,
                ordenaciones);
    }
}
