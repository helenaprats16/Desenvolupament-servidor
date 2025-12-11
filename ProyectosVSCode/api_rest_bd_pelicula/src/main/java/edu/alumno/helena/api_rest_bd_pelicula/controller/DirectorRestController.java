package edu.alumno.helena.api_rest_bd_pelicula.controller;

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

import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationHelper;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.DirectorDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorList;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.srv.DirectorService;
import jakarta.validation.Valid;


@RestController //Indica que esta clase es un controlador web
@RequestMapping("/api/v1/") //defineix el cami base 
public class DirectorRestController {

    private final DirectorService directorService;

    public DirectorRestController(DirectorService directorService) {
        this.directorService = directorService;
    }


    @GetMapping("/directores")
    public ResponseEntity<ListadoRespuesta<DirectorList>> getAllDirectores(
        
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

     
        Pageable pageable = PaginationHelper.createPageable(page, size, sort);

        PaginaDto<DirectorList> paginaDirectorList = directorService.findAllPageDirectorList(pageable);
        
        ListadoRespuesta<DirectorList> response = new ListadoRespuesta<>(
            paginaDirectorList.getNumber(),
            paginaDirectorList.getSize(),
            paginaDirectorList.getTotalElements(),
            paginaDirectorList.getTotalPages(),
            paginaDirectorList.getContent());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/directores/{id}/info")
    public ResponseEntity<DirectorInfo> getDirectorInfoById(
            @PathVariable(value = "id") Long id) throws RuntimeException {
        DirectorInfo directorInfo = directorService.getDirectorInfoById(id);
        return ResponseEntity.ok().body(directorInfo);
    }

    @PostMapping("/directores")
    public ResponseEntity<DirectorInfo> createDirector(@Valid @RequestBody DirectorDb directorDb) {
        DirectorInfo savedDirector = directorService.createDirector(directorDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDirector);
    }

    @PutMapping("/directores/{id}")
    public ResponseEntity<DirectorInfo> updateDirector(
            @PathVariable Long id,
         @Valid @RequestBody DirectorUpdate directorUpdate) {  
        try {
         DirectorInfo updatedDirector = directorService.updateDirector(id, directorUpdate);
         return ResponseEntity.ok(updatedDirector);
        } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/directores/{id}")
    public ResponseEntity<String> deleteDirector(@PathVariable Long id) {
        try {
            directorService.deleteDirectorById(id);
            return ResponseEntity.ok("Director eliminado");

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}

