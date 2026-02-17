package edu.alumno.helena.api_rest_bd_pelicula.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
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
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginaResponse;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationFactory;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationRequest;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PeticionListadoFiltrado;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaList;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculasPorAño;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculasPorGenero;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.srv.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@RestController //Indica que esta clase es un controlador web
@RequestMapping("/api/v1/") //defineix el cami base 
@Validated
public class PeliculaRestController {
    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PaginationFactory paginationFactory;
   

    @GetMapping("/peliculas")
        @Operation(summary = "Listar peliculas", description = "Lista paginada con filtro por nombre")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado correcto"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))
            )
        })
        public ResponseEntity<ListadoRespuesta<PeliculaList>> getAllPeliculas(
                @RequestParam(required = false) String nombre,
                @RequestParam(defaultValue = "0") @Min(0) int page,
                @RequestParam(defaultValue = "3") @Min(1) int size,
                @RequestParam(defaultValue = "id,asc") String[] sort) {
            Set<String> allowedSort = Set.of("id", "titulo", "año", "duracion");
                PaginationRequest paginationRequest = new PaginationRequest(page, size, sort);
            Pageable pageable = paginationFactory.createPageable(paginationRequest, allowedSort);
            PaginaDto<PeliculaList> paginaPeliculaList;
            if (nombre != null && !nombre.isBlank()) {
                paginaPeliculaList = peliculaService.findByNombreContaining(nombre, pageable);
            } else {
                paginaPeliculaList = peliculaService.findAllPagePeliculaList(pageable);
            }
                return ResponseEntity.ok(new ListadoRespuesta<>(
                        paginaPeliculaList.getNumber(),
                        paginaPeliculaList.getSize(),
                        paginaPeliculaList.getTotalElements(),
                        paginaPeliculaList.getTotalPages(),
                        paginaPeliculaList.getContent()));
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
        // Busca i torna la pelicula
        PeliculaInfo peliculaInfo = peliculaService.getPeliculaInfoById(id);
        return ResponseEntity.ok().body(peliculaInfo);
    }


    @GetMapping("/peliculas/orden/{direccionOrden}")
    @Operation(summary = "Ordenar peliculas", description = "Lista peliculas ordenadas por titulo")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado correcto"),
        @ApiResponse(responseCode = "400", description = "Parametros invalidos",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiError.class))
        )
    })
    public ResponseEntity<List<PeliculaList>> getPeliculaListOrderByTitulo(
            @PathVariable("direccionOrden") @NonNull String direccionOrden) {
        List<PeliculaList> lista = peliculaService.findAllPeliculaList(Sort.by(Direction.fromString(direccionOrden), "titulo"));
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/peliculas/all")
    @Operation(summary = "Listar sin paginar", description = "Lista completa sin paginacion")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado correcto")
    })
    public ResponseEntity<List<PeliculaList>> getAllPeliculasNoPage() {
        List<PeliculaList> lista = peliculaService.findAllPeliculaList();
        return ResponseEntity.ok(lista);
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
    public ResponseEntity<List<PeliculaList>> getPeliculasByDirector(
            @PathVariable @Positive Long directorId) {
        List<PeliculaList> lista = peliculaService.findPeliculasByDirector(directorId);
        return ResponseEntity.ok(lista);
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
    public ResponseEntity<List<PeliculaList>> getPeliculasFromYear(@PathVariable @Min(1) Integer year) {
        List<PeliculaList> lista = peliculaService.findPeliculasFromYear(year);
        return ResponseEntity.ok(lista);
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
        // Crea la pelicula i torna la info
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
        // Actualitza sols els camps obtinguts
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
        // Elimina por id si existeix
        peliculaService.deletePeliculaById(id);
        return ResponseEntity.ok("Pelicula eliminada");
    }

    @GetMapping("/peliculas/search")
    @Operation(summary = "Buscar peliculas", description = "Listado paginado con filtros avanzados")
    public ResponseEntity<PaginaResponse<PeliculaList>> searchPeliculasGet(
            @RequestParam(required = false) String[] filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {
        return ResponseEntity.ok(peliculaService.findAll(filter, page, size, sort));
    }

    @PostMapping("/peliculas/search")
    @Operation(summary = "Buscar peliculas (POST)", description = "Listado paginado con filtros en body")
    public ResponseEntity<PaginaResponse<PeliculaList>> searchPeliculasPost(
            @Valid @RequestBody PeticionListadoFiltrado peticionListadoFiltrado) {
        return ResponseEntity.ok(peliculaService.findAll(peticionListadoFiltrado));
    }

    /**
     * CONSULTES AGRUPADES - Estadistiques de pelicules
     */
    
    @GetMapping("/peliculas/estadisticas/por-año")
    @Operation(summary = "Películas agrupadas por año", 
               description = "Obtiene estadísticas de películas agrupadas por año de estreno. Incluye COUNT y AVG de duración por año.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estadísticas obtenidas correctamente",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = PeliculasPorAño.class)))
    })
    public ResponseEntity<List<PeliculasPorAño>> getPeliculasAgrupadasPorAño() {
        return ResponseEntity.ok(peliculaService.getPeliculasAgrupadasPorAño());
    }

    @GetMapping("/peliculas/estadisticas/por-genero")
    @Operation(summary = "Películas agrupadas por género", 
               description = "Obtiene estadísticas de películas agrupadas por género. Incluye COUNT y AVG de duración por género.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estadísticas obtenidas correctamente",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = PeliculasPorGenero.class)))
    })
    public ResponseEntity<List<PeliculasPorGenero>> getPeliculasAgrupadasPorGenero() {
        return ResponseEntity.ok(peliculaService.getPeliculasAgrupadasPorGenero());
    }


}




