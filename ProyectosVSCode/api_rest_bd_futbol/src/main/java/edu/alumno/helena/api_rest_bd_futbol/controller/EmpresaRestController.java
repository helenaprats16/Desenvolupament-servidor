package edu.alumno.helena.api_rest_bd_futbol.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.helena.api_rest_bd_futbol.helper.PaginationHelper;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EmpresaInfo;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EmpresaList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.ListadoRespuesta;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.srv.EmpresaService;


@RestController
@RequestMapping("/api/v1/") 
public class EmpresaRestController {

    private final EmpresaService empresaService;

    public EmpresaRestController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }


    @GetMapping("/empresa")
    public ResponseEntity<ListadoRespuesta<EmpresaList>> getAllEmpresas(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

          
        Pageable pageable = PaginationHelper.createPageable(page, size, sort);


     
        PaginaDto<EmpresaList> paginaEmpresaList = empresaService.findAllPageEmpresaList(pageable);
        
        ListadoRespuesta<EmpresaList> response = new ListadoRespuesta<>(
            paginaEmpresaList.getNumber(),
            paginaEmpresaList.getSize(),
            paginaEmpresaList.getTotalElements(),
            paginaEmpresaList.getTotalPages(),
            paginaEmpresaList.getContent());

        return ResponseEntity.ok(response);
    }


    @GetMapping("/empresa/{id}/info")
    public ResponseEntity<EmpresaInfo> getEmpresaInfo(
            @PathVariable(value = "id") Long id) throws RuntimeException {
        EmpresaInfo empresaInfo = empresaService.getEmpresaInfoById(id);
        return ResponseEntity.ok().body(empresaInfo);
    }


    @DeleteMapping("/empresa/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        try {
            empresaService.deleteEmpresaById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

   
   
    // @PutMapping("path/{id}")
    // public String putMethodName(@PathVariable String id, @RequestBody String entity) {
    //     //TODO: process PUT request
        
    //     return entity;
    // }


}