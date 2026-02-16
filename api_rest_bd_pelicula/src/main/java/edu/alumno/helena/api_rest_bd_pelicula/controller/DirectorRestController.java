package edu.alumno.helena.api_rest_bd_pelicula.controller;

import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.helena.api_rest_bd_pelicula.exception.ApiError;
import edu.alumno.helena.api_rest_bd_pelicula.helper.ListadoRespuestaFactory;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationFactory;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationRequest;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationRequestConverter;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorList;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.srv.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1/")
public class DirectorRestController {

    private final DirectorService directorService;
    private final PaginationRequestConverter paginationRequestConverter;
    private final PaginationFactory paginationFactory;

    /**
     * Constructor para inyectar los servicios y helpers necesarios.
     */
    public DirectorRestController(DirectorService directorService, PaginationRequestConverter paginationRequestConverter, PaginationFactory paginationFactory) {
        this.directorService = directorService;
        this.paginationRequestConverter = paginationRequestConverter;
        this.paginationFactory = paginationFactory;
    }

    @GetMapping("/directores")
    @Operation(summary = "Listar directores", description = "Lista paginada de directores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado correcto"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<ListadoRespuesta<DirectorList>> getAllDirectores(

            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "3") @Min(1) int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        // Campos permitidos para ordenar
        Set<String> allowedSort = Set.of("id", "nombre", "nacionalidad");
        PaginationRequest paginationRequest = paginationRequestConverter.fromParams(page, size, sort);
        // Paginacion + ordenacion con validacion
        Pageable pageable = paginationFactory.createPageable(paginationRequest, allowedSort);

        PaginaDto<DirectorList> paginaDirectorList;
        if (nombre != null && !nombre.isBlank()) {
            // Filtro simple por nombre
            paginaDirectorList = directorService.findByNombreContaining(nombre, pageable);
        } else {
            paginaDirectorList = directorService.findAllPageDirectorList(pageable);
        }

        return ResponseEntity.ok(ListadoRespuestaFactory.fromPagina(paginaDirectorList));
    }

    @GetMapping("/directores/{id}/info")
    @Operation(summary = "Detalle de director", description = "Devuelve un director por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Director encontrado"),
            @ApiResponse(responseCode = "404", description = "Director no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<DirectorInfo> getDirectorInfoById(
            @PathVariable(value = "id") @Positive Long id) throws RuntimeException {
        // Busca y devuelve el director
        DirectorInfo directorInfo = directorService.getDirectorInfoById(id);
        return ResponseEntity.ok().body(directorInfo);
    }

    @PostMapping("/directores")
    @Operation(summary = "Crear director", description = "Crea un director nuevo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Director creado"),
            @ApiResponse(responseCode = "400", description = "Validacion incorrecta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<DirectorInfo> createDirector(@Valid @RequestBody DirectorCreate directorCreate) {
        // Alta de director
        DirectorInfo savedDirector = directorService.createDirector(directorCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDirector);
    }

    @PutMapping("/directores/{id}")
    @Operation(summary = "Actualizar director", description = "Actualiza un director existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Director actualizado"),
            @ApiResponse(responseCode = "404", description = "Director no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<DirectorInfo> updateDirector(
            @PathVariable @Positive Long id,
            @Valid @RequestBody DirectorUpdate directorUpdate) {
        // Actualitza camps editables
        DirectorInfo updatedDirector = directorService.updateDirector(id, directorUpdate);
        return ResponseEntity.ok(updatedDirector);
    }

    @DeleteMapping("/directores/{id}")
    @Operation(summary = "Borrar director", description = "Elimina un director por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Director eliminado"),
            @ApiResponse(responseCode = "404", description = "Director no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<String> deleteDirector(@PathVariable @Positive Long id) {
        // Elimina por id
        directorService.deleteDirectorById(id);
        return ResponseEntity.ok("Director eliminado");
    }

}
