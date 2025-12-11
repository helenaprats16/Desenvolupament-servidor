package edu.alumno.helena.api_rest_bd_pelicula.controller;

import java.util.Collection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationHelper;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaList;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.PeliculaDb;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import edu.alumno.helena.api_rest_bd_pelicula.srv.PeliculaService;


@RestController //Indica que esta clase es un controlador web
@RequestMapping("/api/v1/") //defineix el cami base 
public class PeliculaRestController {

    private final PeliculaService peliculaService;

    public PeliculaRestController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }


    @GetMapping("/peliculas")
    public ResponseEntity<ListadoRespuesta<PeliculaList>> getAllPeliculas(
        
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

     
        Pageable pageable = PaginationHelper.createPageable(page, size, sort);

        PaginaDto<PeliculaList> paginaPeliculaList;
        if (nombre != null && !nombre.isBlank()) {
            paginaPeliculaList = peliculaService.findByNombreContaining(nombre, pageable);
        } else {
            paginaPeliculaList = peliculaService.findAllPagePeliculaList(pageable);
        }
        
        ListadoRespuesta<PeliculaList> response = new ListadoRespuesta<>(
            paginaPeliculaList.getNumber(),
            paginaPeliculaList.getSize(),
            paginaPeliculaList.getTotalElements(),
            paginaPeliculaList.getTotalPages(),
            paginaPeliculaList.getContent());

        return ResponseEntity.ok(response);
    }



    @GetMapping("/peliculas/{id}/info")
    public ResponseEntity<PeliculaInfo> getPeliculaInfoById(
            @PathVariable(value = "id") Long id) throws RuntimeException {
        PeliculaInfo peliculaInfo = peliculaService.getPeliculaInfoById(id);
        return ResponseEntity.ok().body(peliculaInfo);
    }


    @GetMapping("/peliculas/orden/{direccionOrden}")
    public Collection<PeliculaList> getPeliculaListOrderByTitulo(
            @PathVariable("direccionOrden") String direccionOrden) {
        return peliculaService.findAllPeliculaList(Sort.by(Direction.fromString(direccionOrden), "titulo"));
    }


    @GetMapping("/peliculas/all")
    public ResponseEntity<Collection<PeliculaList>> getAllPeliculasNoPage() {
        return ResponseEntity.ok(peliculaService.findAllPeliculaList());
    }

    @GetMapping("/peliculas/director/{directorId}")
    public ResponseEntity<Collection<PeliculaList>> getPeliculasByDirector(@PathVariable Long directorId) {
        return ResponseEntity.ok(peliculaService.findPeliculasByDirector(directorId));
    }

    @GetMapping("/peliculas/year/{year}")
    public ResponseEntity<Collection<PeliculaList>> getPeliculasFromYear(@PathVariable Integer year) {
        return ResponseEntity.ok(peliculaService.findPeliculasFromYear(year));
    }


}




