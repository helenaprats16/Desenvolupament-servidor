package edu.alumno.helena.api_rest_bd_futbol.controller;
import java.util.Collection;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.helena.api_rest_bd_futbol.helper.PaginationHelper;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PorteroList;
import edu.alumno.helena.api_rest_bd_futbol.srv.PorteroService;

@RestController
@RequestMapping("/api/v1/")
public class PorteroRestController {

    private final PorteroService porteroService;

    public PorteroRestController(PorteroService porteroService) {
        this.porteroService = porteroService;
    }


    @PreAuthorize("hasRole('ROLE_ARBITRO')")
    @GetMapping("/porteros")
    public ResponseEntity<ListadoRespuesta<PorteroList>> getAllPorteros(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "idEquipo,asc") String[] sort) {

        //Configurar ordenamiento
        Pageable pageable = PaginationHelper.createPageable(page, size, sort);

        //Recuperar los datos del service
        PaginaDto<PorteroList> paginaPorteroList = porteroService.findAll(pageable);
        //Preprar respuesta

        ListadoRespuesta<PorteroList> response = new ListadoRespuesta<>(
            paginaPorteroList.getNumber(),
            paginaPorteroList.getSize(),
            paginaPorteroList.getTotalElements(),
            paginaPorteroList.getTotalPages(),
            paginaPorteroList.getContent());

        //Devolver Respuesta
        return ResponseEntity.ok(response);
    }


   
}
