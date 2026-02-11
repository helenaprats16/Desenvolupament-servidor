package edu.alumno.helena.api_rest_bd_pelicula.helper;

import edu.alumno.helena.api_rest_bd_pelicula.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;

public class ListadoRespuestaFactory {

    private ListadoRespuestaFactory() {
        throw new UnsupportedOperationException("Esta clase no puede ser instanciada.");
    }

    public static <T> ListadoRespuesta<T> fromPagina(PaginaDto<T> pagina) {
        return new ListadoRespuesta<>(
                pagina.getNumber(),
                pagina.getSize(),
                pagina.getTotalElements(),
                pagina.getTotalPages(),
                pagina.getContent());
    }
}
