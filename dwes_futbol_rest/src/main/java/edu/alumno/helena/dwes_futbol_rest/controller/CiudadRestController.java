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
import edu.alumno.helena.dwes_futbol_rest.model.dto.CiudadInfo;
import edu.alumno.helena.dwes_futbol_rest.model.dto.CiudadList;
import edu.alumno.helena.dwes_futbol_rest.model.dto.PaginaDto;
import edu.alumno.helena.dwes_futbol_rest.srv.CiudadService;

@RestController
@RequestMapping("/api/v1/")
public class CiudadRestController {

    private final CiudadService ciudadService;

    public CiudadRestController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    /*DESACTIVAMOS EL ANTIGUO /ciudades
    @GetMapping("/ciudades")
    public Collection<CiudadList> getCiudadesList() {
        return ciudadService.findAllCiudadList();
    } */


    @GetMapping("/ciudades")
    public ResponseEntity<Map<String, Object>> getAllCiudades(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        try {
            List<Order> criteriosOrdenacion = new ArrayList<Order>();
            if (sort[0].contains(",")) {
                for(String criterioOrdenacion : sort){
                    String[] orden = criterioOrdenacion.split(",");
                    if (orden.length > 1) {
                        criteriosOrdenacion.add(new Order(Direction.fromString(orden[1]), orden[0]));

                    }else{
                    criteriosOrdenacion.add(new Order(Direction.fromString("asc"), orden[0]));

                    }
                }
            }else{
                criteriosOrdenacion.add(new Order(Direction.fromString(sort[1]), sort[0]));
            }
            Sort sorts =Sort.by(criteriosOrdenacion);
            // Crear solicitud de página: n° de página + tamaño + orden
            Pageable paging = PageRequest.of(page, size, sorts);

            PaginaDto<CiudadList> paginaCiudadesList;
            if (nombre == null) // sin filtrar por nombre
                paginaCiudadesList = ciudadService.findAllPageCiudadList(paging);
            else // filtrando nombre
                paginaCiudadesList = ciudadService.findByNombreContaining(nombre, paging);

            // Listado de ciudades a devolver en el servicio REST
            List<CiudadList> ciudades = paginaCiudadesList.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("data", ciudades);
            response.put("currentPage", paginaCiudadesList.getNumber());
            response.put("pageSize", paginaCiudadesList.getSize());
            response.put("totalItems", paginaCiudadesList.getTotalElements());
            response.put("totalPages", paginaCiudadesList.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Se produjo un error interno en el servidor");
            errorResponse.put("details", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






    @GetMapping("/ciudades/nombre/{nombre}")
    public ResponseEntity<Object> getCiudadesByNombre(@PathVariable String nombre) {
        Optional<Object> ciudades = ciudadService.getCiudadesByNombreContainingListOrderByName(
            nombre, 
            Sort.by(Direction.ASC, "nombre")
        );
        if (ciudades.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron ciudades que contengan: " + nombre);
        }
        return ResponseEntity.ok(ciudades.get());
    }

    @GetMapping("/ciudades/orden/{direccionOrden}")
    public Collection<CiudadList> getCiudadesListOrderByName(
            @PathVariable("direccionOrden") String direccionOrden) {
        return ciudadService.findAllCiudadList(Sort.by(Direction.fromString(direccionOrden), "nombre"));
    }

    @GetMapping("/ciudades/{id}/info")
    public ResponseEntity<CiudadInfo> getCiudadInfoById(
            @PathVariable(value = "id") Long id) throws RuntimeException {
        Optional<CiudadInfo> ciudadInfo = ciudadService.getCiudadInfoById(id);
        if (ciudadInfo.isPresent())
            return ResponseEntity.ok().body(ciudadInfo.get());
        else
            throw new ResourceNotFoundException("Ciudad not found on :: " + id);
    }



}
