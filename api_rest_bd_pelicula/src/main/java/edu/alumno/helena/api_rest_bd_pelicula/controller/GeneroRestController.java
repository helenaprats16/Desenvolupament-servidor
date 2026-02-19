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
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginaResponse;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationFactory;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationRequest;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PeticionListadoFiltrado;
import edu.alumno.helena.api_rest_bd_pelicula.srv.GeneroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/generos")
public class GeneroRestController {
    
    private final GeneroService generoService ;
    private final PaginationFactory paginationFactory;

    public GeneroRestController(GeneroService generoService, PaginationFactory paginationFactory) {
        this.generoService = generoService;
        this.paginationFactory = paginationFactory;
    }
   
    /**
     * Obtiene una lista paginada de géneros, con opción de filtrar por nombre y
     * ordenar por campos permitidos.
     *
     * @param nombre Filtro opcional por nombre de género.
     * @param page   Número de página (por defecto 0).
     * @param size   Cantidad de elementos por página (por defecto 3).
     * @param sort   Ordenación en formato campo,dirección (por defecto id,asc).
     * @return Respuesta con la lista paginada de géneros.
     */

    @Operation(summary = "Llista gèneres", description = "Retorna una llista paginada de gèneres, amb filtre opcional per nom i ordenació.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Llistat correcte", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListadoRespuesta.class))),
        @ApiResponse(responseCode = "400", description = "Paràmetres invàlids", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping
    public ResponseEntity<ListadoRespuesta<GeneroInfo>> list(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "3") @Min(1) int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {
        Set<String> allowedSort = Set.of("id", "nombre");
        PaginationRequest paginationRequest = new PaginationRequest(page, size, sort);
        Pageable pageable = paginationFactory.createPageable(paginationRequest, allowedSort);
        PaginaDto<GeneroInfo> paginaGenero = (nombre != null && !nombre.isBlank())
                ? generoService.findByNombreContaining(nombre, pageable)
                : generoService.findAllPageGeneros(pageable);
        return ResponseEntity.ok(new ListadoRespuesta<>(
            paginaGenero.getNumber(),
            paginaGenero.getSize(),
            paginaGenero.getTotalElements(),
            paginaGenero.getTotalPages(),
            paginaGenero.getContent()));
    }

    /**
     * Obtiene el detalle de un género a partir de su ID.
     *
     * @param id ID del género a buscar.
     * @return Detalle del género encontrado.
     */

    /**
     * Obté el detall d'un gènere pel seu ID.
     * @param id ID del gènere
     * @return Detall del gènere
     */
    @Operation(summary = "Detall de gènere", description = "Retorna el detall d'un gènere pel seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gènere trobat", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneroInfo.class))),
        @ApiResponse(responseCode = "404", description = "Gènere no trobat", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<GeneroInfo> read(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(generoService.getGeneroInfoById(id));
    }

    /**
     * Crea un nuevo género en el sistema.
     *
     * @param generoCreate Objeto con los datos del género a crear.
     * @return Género creado correctamente.
     */

    /**
     * Crea un nou gènere.
     * @param generoCreate Dades del gènere
     * @param bindingResult Resultat de la validació
     * @return Gènere creat
     */
    @Operation(summary = "Crea un nou gènere")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Creat correctament", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneroInfo.class))),
        @ApiResponse(responseCode = "400", description = "Error de validació", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        @ApiResponse(responseCode = "409", description = "Nom duplicat", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping
    public ResponseEntity<GeneroInfo> create(@Valid @RequestBody GeneroCreate generoCreate) {
        GeneroInfo savedGenero = generoService.createGenero(generoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenero);
    }

    /**
     * Actualiza los datos de un género existente.
     *
     * @param id           ID del género a actualizar.
     * @param generoUpdate Objeto con los datos actualizados.
     * @return Género actualizado correctamente.
     */

    /**
     * Actualitza un gènere existent.
     * @param id ID del gènere
     * @param generoUpdate Dades noves
     * @param bindingResult Resultat de la validació
     * @return Gènere actualitzat
     */
    @Operation(summary = "Actualitza un gènere")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Actualitzat correctament", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneroInfo.class))),
        @ApiResponse(responseCode = "400", description = "Error de validació", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        @ApiResponse(responseCode = "404", description = "No trobat", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<GeneroInfo> update(@PathVariable @Positive Long id, @Valid @RequestBody GeneroUpdate generoUpdate) {
        return ResponseEntity.ok(generoService.updateGenero(id, generoUpdate));
    }

    /**
     * Elimina un género del sistema a partir de su ID.
     *
     * @param id ID del género a eliminar.
     * @return Mensaje de confirmación de borrado.
     */

    /**
     * Elimina un gènere pel seu ID.
     * @param id ID del gènere
     * @return Sense contingut si s'elimina correctament
     */
    @Operation(summary = "Elimina un gènere")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Genere eliminat", content = @Content),
        @ApiResponse(responseCode = "404", description = "No trobat", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Positive Long id) {
        generoService.deleteGeneroById(id);
        return ResponseEntity.ok("Genero eliminado");
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar generos", description = "Listado paginado con filtros avanzados")
    public ResponseEntity<PaginaResponse<GeneroInfo>> searchGenerosGet(
            @RequestParam(required = false) String[] filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {
        return ResponseEntity.ok(generoService.findAll(filter, page, size, sort));
    }

    @PostMapping("/search")
    @Operation(summary = "Buscar generos (POST)", description = "Listado paginado con filtros en body")
    public ResponseEntity<PaginaResponse<GeneroInfo>> searchGenerosPost(
            @Valid @RequestBody PeticionListadoFiltrado peticionListadoFiltrado) {
        return ResponseEntity.ok(generoService.findAll(peticionListadoFiltrado));
    }
}
