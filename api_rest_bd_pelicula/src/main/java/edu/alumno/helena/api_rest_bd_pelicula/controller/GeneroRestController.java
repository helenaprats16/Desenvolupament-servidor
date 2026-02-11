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
import org.springframework.validation.annotation.Validated;

import edu.alumno.helena.api_rest_bd_pelicula.exception.ApiError;
import edu.alumno.helena.api_rest_bd_pelicula.helper.ListadoRespuestaFactory;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationFactory;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationRequest;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationRequestConverter;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.srv.GeneroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Generos", description = "Endpoint simple para crear generos")
@RestController
@RequestMapping("/api/v1/")
@Validated
public class GeneroRestController {

    private final GeneroService generoService;
    private final PaginationRequestConverter paginationRequestConverter;
    private final PaginationFactory paginationFactory;

    public GeneroRestController(GeneroService generoService,
            PaginationRequestConverter paginationRequestConverter,
            PaginationFactory paginationFactory) {
        this.generoService = generoService;
        this.paginationRequestConverter = paginationRequestConverter;
        this.paginationFactory = paginationFactory;
    }

    @GetMapping("/generos")
    @Operation(summary = "Listar generos", description = "Lista paginada de generos con filtro por nombre")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado correcto"),
        @ApiResponse(responseCode = "400", description = "Parametros invalidos",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "BadRequest", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":400,\"error\":\"Bad Request\",\"message\":\"El parametro size debe ser mayor que 0\",\"path\":\"/api/v1/generos\"}")))
    })
    public ResponseEntity<ListadoRespuesta<GeneroInfo>> getAllGeneros(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "3") @Min(1) int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        // Campos permitidos para ordenar
        Set<String> allowedSort = Set.of("id", "nombre");
        PaginationRequest paginationRequest = paginationRequestConverter.fromParams(page, size, sort);
        // Paginacion + ordenacion con validacion
        Pageable pageable = paginationFactory.createPageable(paginationRequest, allowedSort);

        PaginaDto<GeneroInfo> paginaGenero;
        if (nombre != null && !nombre.isBlank()) {
            // Filtro simple por nombre
            paginaGenero = generoService.findByNombreContaining(nombre, pageable);
        } else {
            paginaGenero = generoService.findAllPageGeneros(pageable);
        }

        return ResponseEntity.ok(ListadoRespuestaFactory.fromPagina(paginaGenero));
    }

    @GetMapping("/generos/{id}")
    @Operation(summary = "Detalle de genero", description = "Devuelve un genero por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Genero encontrado"),
        @ApiResponse(responseCode = "404", description = "Genero no encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "NotFound", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":404,\"error\":\"Not Found\",\"message\":\"Genero no encontrado: 999\",\"path\":\"/api/v1/generos/999\"}")))
    })
    public ResponseEntity<GeneroInfo> getGeneroById(@PathVariable @Positive Long id) {
        // Busca y devuelve el genero
        return ResponseEntity.ok(generoService.getGeneroInfoById(id));
    }

    @PostMapping("/generos")
    @Operation(summary = "Crear genero", description = "Crea un genero nuevo")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Genero creado"),
        @ApiResponse(responseCode = "400", description = "Validacion incorrecta",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "Validation", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":400,\"error\":\"Bad Request\",\"message\":\"Validacion de campos\",\"path\":\"/api/v1/generos\",\"fieldErrors\":{\"nombre\":\"El nombre no puede ser vacio\"}}"))),
        @ApiResponse(responseCode = "409", description = "Nombre duplicado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "Conflict", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":409,\"error\":\"Conflict\",\"message\":\"Violacion de integridad de datos\",\"path\":\"/api/v1/generos\"}")))
    })
    public ResponseEntity<GeneroInfo> createGenero(@Valid @RequestBody GeneroCreate generoCreate) {
        // Alta de genero
        GeneroInfo savedGenero = generoService.createGenero(generoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenero);
    }

    @PutMapping("/generos/{id}")
    @Operation(summary = "Actualizar genero", description = "Actualiza un genero existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Genero actualizado"),
        @ApiResponse(responseCode = "404", description = "Genero no encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "NotFound", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":404,\"error\":\"Not Found\",\"message\":\"Genero no encontrado: 999\",\"path\":\"/api/v1/generos/999\"}")))
    })
    public ResponseEntity<GeneroInfo> updateGenero(
            @PathVariable @Positive Long id,
            @Valid @RequestBody GeneroUpdate generoUpdate) {
        // Actualiza campos editables
        return ResponseEntity.ok(generoService.updateGenero(id, generoUpdate));
    }

    @DeleteMapping("/generos/{id}")
    @Operation(summary = "Borrar genero", description = "Elimina un genero por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Genero eliminado"),
        @ApiResponse(responseCode = "404", description = "Genero no encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "NotFound", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":404,\"error\":\"Not Found\",\"message\":\"Genero no encontrado: 999\",\"path\":\"/api/v1/generos/999\"}")))
    })
    public ResponseEntity<String> deleteGenero(@PathVariable @Positive Long id) {
        // Elimina por id
        generoService.deleteGeneroById(id);
        return ResponseEntity.ok("Genero eliminado");
    }
}
