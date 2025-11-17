package edu.alumno.helena.api_rest_bd_futbol.controller;
import java.util.Collection;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.helena.api_rest_bd_futbol.helper.PaginationHelper;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.JugadorList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.srv.JugadorService;

@RestController
@RequestMapping("/api/v1/")
public class JugadorRestController {

    private final JugadorService jugadorService;

    public JugadorRestController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }


    @GetMapping("/jugadores")
    public ResponseEntity<ListadoRespuesta<JugadorList>> getAllJugador(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "idEquipo,asc") String[] sort) {

        //Configurar ordenamiento
        Pageable pageable = PaginationHelper.createPageable(page, size, sort);

        //Recuperar los datos del service
        PaginaDto<JugadorList> paginaJugadorList = jugadorService.findAll(pageable);
        //Preprar respuesta

        ListadoRespuesta<JugadorList> response = new ListadoRespuesta<>(
            paginaJugadorList.getNumber(),
            paginaJugadorList.getSize(),
            paginaJugadorList.getTotalElements(),
            paginaJugadorList.getTotalPages(),
            paginaJugadorList.getContent());

        //Devolver Respuesta
        return ResponseEntity.ok(response);
    }


   
}
