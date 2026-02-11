package edu.alumno.helena.api_rest_bd_futbol.srv;

import edu.alumno.helena.api_rest_bd_futbol.exception.FiltroException;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.JugadorList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaResponse;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PeticionListadoFiltrado;

public interface JugadorService {
    /**
     * Busca jugadores aplicando filtros, paginación y ordenación a partir de parámetros individuales.
     * 
     * @param filter Array de strings con los filtros en formato "campo:operador:valor"
     * @param page Número de página (zero-based)
     * @param size Tamaño de cada página
     * @param sort Array con criterios de ordenación en formato "campo", "campo,asc" o "campo,desc"
     * @return PaginaResponse con la lista de jugadores filtrada y paginada
     * @throws FiltroException Si hay errores en los filtros o la ordenación (errorCodes: 'BAD_OPERATOR_FILTER','BAD_ATTRIBUTE_ORDER','BAD_ATTRIBUTE_FILTER','BAD_FILTER')
     */
    public PaginaResponse<JugadorList> findAll(String[] filter, int page, int size, String[] sort) throws FiltroException;
     /**
     * Busca jugadores aplicando filtros, paginación y ordenación a partir de una petición estructurada.
     * 
     * @param peticionListadoFiltrado Objeto que encapsula los criterios de búsqueda (nº pagina, tamaño de la página, lista de filtros y lista de ordenaciones)
     * @return PaginaResponse con la lista de jugadores filtrada y paginada
     * @throws FiltroException Si hay errores en los filtros o la ordenación (errorCodes: 'BAD_OPERATOR_FILTER','BAD_ATTRIBUTE_ORDER','BAD_ATTRIBUTE_FILTER','BAD_FILTER')
     */
    public PaginaResponse<JugadorList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException;
}
