package edu.alumno.helena.api_rest_bd_pelicula.controller;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import edu.alumno.helena.api_rest_bd_pelicula.helper.ListadoRespuestaFactory;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationFactory;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationRequest;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationRequestConverter;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaList;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import edu.alumno.helena.api_rest_bd_pelicula.srv.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Peliculas", description = "CRUD y consultas de peliculas")
@RestController //Indica que esta clase es un controlador web
@RequestMapping("/api/v1/") //defineix el cami base 
@Validated
public class PeliculaRestController {

    private final PeliculaService peliculaService;
    private final PaginationRequestConverter paginationRequestConverter;
    private final PaginationFactory paginationFactory;

    public PeliculaRestController(PeliculaService peliculaService,
            PaginationRequestConverter paginationRequestConverter,
            PaginationFactory paginationFactory) {
        this.peliculaService = peliculaService;
        this.paginationRequestConverter = paginationRequestConverter;
        this.paginationFactory = paginationFactory;
    }


    @GetMapping("/peliculas")
    @Operation(summary = "Listar peliculas", description = "Lista paginada con filtro por nombre")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado correcto"),
        @ApiResponse(responseCode = "400", description = "Parametros invalidos",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "BadRequest", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":400,\"error\":\"Bad Request\",\"message\":\"Campo sort no valido: campoInexistente\",\"path\":\"/api/v1/peliculas\"}")))
    })
    public ResponseEntity<ListadoRespuesta<PeliculaList>> getAllPeliculas(
        
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "3") @Min(1) int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        // Campos permitidos para ordenar
        Set<String> allowedSort = Set.of("id", "titulo", "año", "duracion");
        PaginationRequest paginationRequest = paginationRequestConverter.fromParams(page, size, sort);
        // Paginacion + ordenacion con validacion
        Pageable pageable = paginationFactory.createPageable(paginationRequest, allowedSort);

        PaginaDto<PeliculaList> paginaPeliculaList;
        if (nombre != null && !nombre.isBlank()) {
            // Filtro simple por titulo
            paginaPeliculaList = peliculaService.findByNombreContaining(nombre, pageable);
        } else {
            paginaPeliculaList = peliculaService.findAllPagePeliculaList(pageable);
        }
        
        return ResponseEntity.ok(ListadoRespuestaFactory.fromPagina(paginaPeliculaList));
    }



    @GetMapping("/peliculas/{id}/info")
    @Operation(summary = "Detalle de pelicula", description = "Devuelve una pelicula por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pelicula encontrada"),
        @ApiResponse(responseCode = "404", description = "Pelicula no encontrada",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "NotFound", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":404,\"error\":\"Not Found\",\"message\":\"Pelicula no encontrada: 999\",\"path\":\"/api/v1/peliculas/999/info\"}")))
    })
    public ResponseEntity<PeliculaInfo> getPeliculaInfoById(
            @PathVariable(value = "id") @Positive Long id) throws RuntimeException {
        // Busca y devuelve la pelicula
        PeliculaInfo peliculaInfo = peliculaService.getPeliculaInfoById(id);
        return ResponseEntity.ok().body(peliculaInfo);
    }


    @GetMapping("/peliculas/orden/{direccionOrden}")
    @Operation(summary = "Ordenar peliculas", description = "Lista peliculas ordenadas por titulo")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado correcto"),
        @ApiResponse(responseCode = "400", description = "Parametros invalidos",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "BadRequest", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":400,\"error\":\"Bad Request\",\"message\":\"Direccion de orden no valida: descx\",\"path\":\"/api/v1/peliculas/orden/descx\"}")))
    })
    @NonNull  
    public Collection<PeliculaList> getPeliculaListOrderByTitulo(
            @PathVariable("direccionOrden") @NonNull String direccionOrden) {
        // Ordena por titulo segun la direccion indicada
        return peliculaService.findAllPeliculaList(Sort.by(Direction.fromString(direccionOrden), "titulo"));
    }


    @GetMapping("/peliculas/all")
    @Operation(summary = "Listar sin paginar", description = "Lista completa sin paginacion")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado correcto")
    })
    public ResponseEntity<Collection<PeliculaList>> getAllPeliculasNoPage() {
        // Lista completa sin paginacion
        return ResponseEntity.ok(peliculaService.findAllPeliculaList());
    }

    @GetMapping("/peliculas/director/{directorId}")
    @Operation(summary = "Peliculas por director", description = "Lista peliculas de un director")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado correcto"),
        @ApiResponse(responseCode = "404", description = "Director no encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "NotFound", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":404,\"error\":\"Not Found\",\"message\":\"Director no encontrado: 999\",\"path\":\"/api/v1/peliculas/director/999\"}")))
    })
    public ResponseEntity<Collection<PeliculaList>> getPeliculasByDirector(
            @PathVariable @Positive Long directorId) {
        // Devuelve solo peliculas del director
        return ResponseEntity.ok(peliculaService.findPeliculasByDirector(directorId));
    }

    @GetMapping("/peliculas/year/{year}")
    @Operation(summary = "Peliculas por anio", description = "Lista peliculas por anio")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado correcto"),
        @ApiResponse(responseCode = "404", description = "No hay peliculas para el anio indicado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "NotFound", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":404,\"error\":\"Not Found\",\"message\":\"No hay peliculas para el anio: 1900\",\"path\":\"/api/v1/peliculas/year/1900\"}")))
    })
    public ResponseEntity<Collection<PeliculaList>> getPeliculasFromYear(@PathVariable @Min(1) Integer year) {
        // Filtra por anio de estreno
        return ResponseEntity.ok(peliculaService.findPeliculasFromYear(year));
    }

    @PostMapping("/peliculas")
    @Operation(summary = "Crear pelicula", description = "Crea una pelicula nueva")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pelicula creada"),
        @ApiResponse(responseCode = "400", description = "Validacion incorrecta",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "Validation", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":400,\"error\":\"Bad Request\",\"message\":\"Validacion de campos\",\"path\":\"/api/v1/peliculas\",\"fieldErrors\":{\"titulo\":\"El titulo no puede estar vacio\"}}"))),
        @ApiResponse(responseCode = "409", description = "Violacion de integridad",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "Conflict", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":409,\"error\":\"Conflict\",\"message\":\"Violacion de integridad de datos\",\"path\":\"/api/v1/peliculas\"}")))
    })
    public ResponseEntity<PeliculaInfo> createPelicula(@Valid @RequestBody PeliculaCreate peliculaCreate) {
        // Crea la pelicula y devuelve su info
        PeliculaInfo savedPelicula = peliculaService.createPelicula(peliculaCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPelicula);
    }

    @PutMapping("/peliculas/{id}")
    @Operation(summary = "Actualizar pelicula", description = "Actualiza una pelicula existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pelicula actualizada"),
        @ApiResponse(responseCode = "400", description = "Validacion incorrecta",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "Validation", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":400,\"error\":\"Bad Request\",\"message\":\"Validacion de campos\",\"path\":\"/api/v1/peliculas/1\",\"fieldErrors\":{\"titulo\":\"El titulo no puede estar vacio\"}}"))),
        @ApiResponse(responseCode = "404", description = "Pelicula no encontrada",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "NotFound", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":404,\"error\":\"Not Found\",\"message\":\"Pelicula no encontrada: 999\",\"path\":\"/api/v1/peliculas/999\"}")))
    })
    public ResponseEntity<PeliculaInfo> updatePelicula(
            @PathVariable @Positive Long id,
            @Valid @RequestBody PeliculaUpdate peliculaUpdate) {
        // Actualiza solo los campos recibidos
        PeliculaInfo updatedPelicula = peliculaService.updatePelicula(id, peliculaUpdate);
        return ResponseEntity.ok(updatedPelicula);
    }

    @DeleteMapping("/peliculas/{id}")
    @Operation(summary = "Borrar pelicula", description = "Elimina una pelicula por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pelicula eliminada"),
        @ApiResponse(responseCode = "404", description = "Pelicula no encontrada",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class),
                examples = @ExampleObject(name = "NotFound", value = "{\"timestamp\":\"2026-02-11T10:15:30\",\"status\":404,\"error\":\"Not Found\",\"message\":\"Pelicula no encontrada: 999\",\"path\":\"/api/v1/peliculas/999\"}")))
    })
    public ResponseEntity<String> deletePelicula(@PathVariable @Positive Long id) {
        // Elimina por id si existe
        peliculaService.deletePeliculaById(id);
        return ResponseEntity.ok("Pelicula eliminada");
    }


}




