package edu.alumno.helena.api_rest_bd_futbol.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.helena.api_rest_bd_futbol.exception.CustomErrorResponse;
import edu.alumno.helena.api_rest_bd_futbol.exception.FiltroException;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.JugadorList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaResponse;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PeticionListadoFiltrado;
import edu.alumno.helena.api_rest_bd_futbol.srv.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class JugadorRestController {
  private JugadorService jugadorService;
 /**
   * Constructor para inyectar el servicio de jugadores.
   *
   * @param jugadorService Servicio que maneja la lógica de negocio de los jugadores.
   */
  public JugadorRestController(JugadorService jugadorService) {
    this.jugadorService = jugadorService;
  }
/**
   * Obtiene una lista paginada de jugadores con opción de filtrado y ordenación.
   *
   * @param filter Opcional. Filtros de búsqueda en formato `campo:operador:valor` (Ej: "nombre:contiene:Messi").
   * @param page Número de página (por defecto 0).
   * @param size Cantidad de elementos por página (por defecto 3).
   * @param sort Ordenación en formato `campo,direccion` (Ej: "idEquipo,asc").
   * @return Una respuesta con la lista paginada de jugadores.
   * @throws FiltroException Si ocurre un error en la aplicación de filtros.
   */
  @Operation(summary = "Obtener jugadores paginados", description = "Retorna una lista paginada de jugadores aplicando filtros y ordenación opcionales.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista de jugadores obtenida correctamente", 
    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PaginaResponse.class)) }), 
    @ApiResponse(responseCode = "400", description = "Bad Request: Errores de filtrado u ordenación (errorCodes: 'BAD_OPERATOR_FILTER','BAD_ATTRIBUTE_ORDER','BAD_ATTRIBUTE_FILTER','BAD_FILTER'", 
    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) }), 
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")  })
  @GetMapping("/jugadores")
  public ResponseEntity<PaginaResponse<JugadorList>> getAllJugadores(
      @RequestParam(required = false) String[] filter,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "3") int size,
      @RequestParam(defaultValue = "idEquipo,asc") String[] sort) throws FiltroException {
    return ResponseEntity.ok(jugadorService.findAll(filter, page, size, sort));
  }

   /**
   * Obtiene una lista paginada de jugadores mediante una solicitud POST con un objeto de filtros.
   *
   * @param peticionListadoFiltrado Objeto con los filtros de búsqueda y opciones de paginación.
   * @return Una respuesta con la lista paginada de jugadores.
   * @throws FiltroException Si ocurre un error en la aplicación de filtros.
   */
  @Operation(summary = "Obtener jugadores con filtros avanzados", description = "Retorna jugadores paginados enviando los filtros en el cuerpo de la petición.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Lista de jugadores obtenida correctamente", 
      content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PaginaResponse.class)) }), 
      @ApiResponse(responseCode = "400", description = "Bad Request: Errores de filtrado u ordenación (errorCodes: 'BAD_OPERATOR_FILTER','BAD_ATTRIBUTE_ORDER','BAD_ATTRIBUTE_FILTER','BAD_FILTER')", 
      content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) }), 
      @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @PostMapping("/jugadores")
  public ResponseEntity<PaginaResponse<JugadorList>> getAllJugadoresPost(
      @Valid @RequestBody PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException {
    return ResponseEntity.ok(jugadorService.findAll(
        peticionListadoFiltrado));
  }
}
