package edu.alumno.helena.api_rest_bd_futbol.controller;
import java.util.Collection;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.helena.api_rest_bd_futbol.helper.PaginationHelper;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.CiudadInfo;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.CiudadList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.srv.CiudadService;

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
    public ResponseEntity<ListadoRespuesta<CiudadList>> getAllCiudades(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        //Configurar ordenamiento
        Pageable pageable = PaginationHelper.createPageable(page, size, sort);

        //Recuperar los datos del service
        PaginaDto<CiudadList> paginaCiudadList = ciudadService.findAllPageCiudadList(pageable);
        //Preprar respuesta

        ListadoRespuesta<CiudadList> response = new ListadoRespuesta<>(
            paginaCiudadList.getNumber(),
            paginaCiudadList.getSize(),
            paginaCiudadList.getTotalElements(),
            paginaCiudadList.getTotalPages(),
            paginaCiudadList.getContent());

        //Devolver Respuesta
        return ResponseEntity.ok(response);
    }


    @GetMapping("/ciudades/orden/{direccionOrden}")
    public Collection<CiudadList> getCiudadesListOrderByName(
            @PathVariable("direccionOrden") String direccionOrden) {
        return ciudadService.findAllCiudadList(Sort.by(Direction.fromString(direccionOrden), "nombre"));
    }

    @GetMapping("/ciudades/nombre/{likeCiudad}/orden/{direccionOrden}")
    public Collection<CiudadList> getCiudadesByNombreContainingListOrderByName(@PathVariable String nombre) {
        return ciudadService.findAllCiudadList(Sort.by(Direction.fromString(nombre), "nombre"));
       
    }

    

    @GetMapping("/ciudades/{id}/info")
    public ResponseEntity<CiudadInfo> getCiudadInfoById(
            @PathVariable(value = "id") Long id) throws RuntimeException {
        CiudadInfo ciudadInfo = ciudadService.getCiudadInfoById(id);
        return ResponseEntity.ok().body(ciudadInfo);
    }



}
