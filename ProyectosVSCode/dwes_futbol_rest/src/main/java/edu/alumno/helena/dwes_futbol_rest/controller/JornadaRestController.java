package edu.alumno.helena.dwes_futbol_rest.controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.helena.dwes_futbol_rest.exception.ResourceNotFoundException;
import edu.alumno.helena.dwes_futbol_rest.model.dto.JornadaList;
import edu.alumno.helena.dwes_futbol_rest.model.dto.JornadaInfo;
import edu.alumno.helena.dwes_futbol_rest.model.dto.PaginaDto;
import edu.alumno.helena.dwes_futbol_rest.srv.JornadaService;


@RestController
@RequestMapping("/api/v1/")
public class JornadaRestController {

    private final JornadaService jornadaService;

    public JornadaRestController(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    @GetMapping("/jornadas")
    public ResponseEntity<Map<String, Object>> getAllJornadas(
            @RequestParam(required = false) String fecha,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "num,asc") String[] sort) {

        try {
            List<Order> criteriosOrdenacion = new ArrayList<Order>();
            if (sort[0].contains(",")) {
                for(String criterioOrdenacion : sort){
                    String[] orden = criterioOrdenacion.split(",");
                    if (orden.length > 1) {
                        criteriosOrdenacion.add(new Order(Direction.fromString(orden[1]), orden[0]));
                    } else {
                        criteriosOrdenacion.add(new Order(Direction.fromString("asc"), orden[0]));
                    }
                }
            } else {
                criteriosOrdenacion.add(new Order(Direction.fromString(sort[1]), sort[0]));
            }
            Sort sorts = Sort.by(criteriosOrdenacion);
            // Crear solicitud de página: n° de página + tamaño + orden
            Pageable paging = PageRequest.of(page, size, sorts);

            PaginaDto<JornadaList> paginaJornadasList;
            if (fecha == null) // sin filtrar por fecha
                paginaJornadasList = jornadaService.findAllPageJornadaList(paging);
            else // filtrando por fecha
                paginaJornadasList = jornadaService.findByFechaContaining(fecha, paging);

            // Listado de jornadas a devolver en el servicio REST
            List<JornadaList> jornadas = paginaJornadasList.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("data", jornadas);
            response.put("currentPage", paginaJornadasList.getNumber());
            response.put("pageSize", paginaJornadasList.getSize());
            response.put("totalItems", paginaJornadasList.getTotalElements());
            response.put("totalPages", paginaJornadasList.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Se produjo un error interno en el servidor");
            errorResponse.put("details", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener todas las jornadas sin paginación
    @GetMapping("/jornadas/todas")
    public Collection<JornadaList> getAllJornadas() {
        return jornadaService.findAllJornadaList();
    }

    // Método para obtener jornadas por fecha
    @GetMapping("/jornadas/fecha/{fecha}")
    public ResponseEntity<Object> getJornadasByFecha(@PathVariable String fecha) {
        // Para este método necesitarías agregar un método en el servicio
        // Por ahora usaremos el existente con Pageable
        Pageable paging = PageRequest.of(0, 10); // página 0, tamaño 10
        PaginaDto<JornadaList> jornadas = jornadaService.findByFechaContaining(fecha, paging);
        
        if (jornadas.getContent().isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron jornadas con fecha: " + fecha);
        }
        return ResponseEntity.ok(jornadas.getContent());
    }

    @GetMapping("/jornadas/orden/{direccionOrden}")
    public Collection<JornadaList> getJornadasListOrderByName(
            @PathVariable("direccionOrden") String direccionOrden) {
        return jornadaService.findAllJornadadList(Sort.by(Direction.fromString(direccionOrden), "fecha"));
    }

    @GetMapping("/jornadas/{num}/info")
    public ResponseEntity<JornadaInfo> getJornadaInfoByNum(
            @PathVariable(value = "num") Long num) throws RuntimeException {
        Optional<JornadaInfo> jornadaInfo = jornadaService.getJornadaInfoByNum(num);
        if (jornadaInfo.isPresent())
            return ResponseEntity.ok().body(jornadaInfo.get());
        else
            throw new ResourceNotFoundException("Jornada not found with num: " + num);
    }

    // Método adicional para obtener jornadas ordenadas por diferentes campos
    @GetMapping("/jornadas/ordenadas")
    public Collection<JornadaList> getJornadasOrdenadas(
            @RequestParam(defaultValue = "fecha") String campo,
            @RequestParam(defaultValue = "asc") String direccion) {
        return jornadaService.findAllJornadadList(Sort.by(Direction.fromString(direccion), campo));
    }
}