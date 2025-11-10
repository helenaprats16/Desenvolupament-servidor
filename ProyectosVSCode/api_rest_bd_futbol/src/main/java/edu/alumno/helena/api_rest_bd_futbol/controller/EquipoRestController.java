package edu.alumno.helena.api_rest_bd_futbol.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.helena.api_rest_bd_futbol.helper.PaginationHelper;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EquipoList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.srv.EquipoService;

@RestController
@RequestMapping("/api/v1/")
public class EquipoRestController {

    private final EquipoService equipoService;

    public EquipoRestController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }


    @GetMapping("/equipos")
    public ResponseEntity<ListadoRespuesta<EquipoList>> getAllEquipos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        //Configurar ordenamiento
        Pageable pageable = PaginationHelper.createPageable(page, size, sort);

        //Recuperar los datos del service
        PaginaDto<EquipoList> paginaEquiposList = equipoService.findAll(pageable);
        //Preprar respuesta

        ListadoRespuesta<EquipoList> response = new ListadoRespuesta<>(
            paginaEquiposList.getNumber(),
            paginaEquiposList.getSize(),
            paginaEquiposList.getTotalElements(),
            paginaEquiposList.getTotalPages(),
            paginaEquiposList.getContent());

        //Devolver Respuesta
        return ResponseEntity.ok(response);
    }




}
