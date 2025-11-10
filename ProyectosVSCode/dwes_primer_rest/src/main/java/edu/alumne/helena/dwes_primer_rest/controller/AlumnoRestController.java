package edu.alumne.helena.dwes_primer_rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.alumne.helena.dwes_primer_rest.exception.AlumnoNotFoundExcepcion;
import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoEdit;
import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoInfo;
import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoList;
import edu.alumne.helena.dwes_primer_rest.service.AlumnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class AlumnoRestController {

    private AlumnoService alumnoService;

    public AlumnoRestController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping("/alumnos")
    public List<AlumnoList> getAlumnosList() {
        return alumnoService.findAllAlumnoList();
    }

    @PostMapping("/alumnos")
    public AlumnoEdit newAlumnoEdit(@Valid @RequestBody AlumnoEdit alumnoEdit) {
        return alumnoService.save(alumnoEdit);
    }

    @Operation(summary ="Get a Student by its DNI")
    @ApiResponses(value = {
        @ApiResponse(responseCode= "200", description= "Found the Student",
        content= { @Content(mediaType= "application/json",
        schema= @Schema(implementation= AlumnoEdit.class)) }),
    @ApiResponse(responseCode= "404", description="Student not found",
    content= @Content) })



    @GetMapping("/alumnos/{dni}")
    public ResponseEntity<AlumnoEdit> getAlumnoEditByDni(
        @Parameter(description = "Dni of Student to be searched") @PathVariable(value = "dni") String dni) throws RuntimeException {
        Optional<AlumnoEdit> alumnoEdit = alumnoService.getAlumnoEditByDni(dni);
        if (alumnoEdit.isPresent())
            return ResponseEntity.ok().body(alumnoEdit.get());
        else
            throw new AlumnoNotFoundExcepcion("STUDENT_NOT_FOUND", "Student with DNI " + dni + " not found");
    }

    @GetMapping("/alumnos/{dni}/info")
    public ResponseEntity<AlumnoInfo> getAlumnoInfoByDni(
            @PathVariable(value = "dni") String dni) throws RuntimeException {
        Optional<AlumnoInfo> alumnoInfo = alumnoService.getAlumnoInfoByDni(dni);
        if (alumnoInfo.isPresent())
            return ResponseEntity.ok().body(alumnoInfo.get());
        else
            throw new AlumnoNotFoundExcepcion("STUDENT_NOT_FOUND", "Student with DNI " + dni + " not found");
    }

    @PutMapping("/alumnos/{dni}")
    public ResponseEntity<AlumnoEdit> updateAlumnoEdit(@PathVariable(value = "dni") String dni,
            @Valid @RequestBody AlumnoEdit alumnoEdit) throws RuntimeException {

        Optional<AlumnoEdit> alumnoEditOptional = alumnoService.update(alumnoEdit);
        if (alumnoEditOptional.isPresent())
            return ResponseEntity.ok().body(alumnoEditOptional.get());
        else
            throw new RuntimeException("Error RuntimeException");
    }

    @DeleteMapping("/alumnos/{dni}")
    public String deleteByDni(@PathVariable(value = "dni") String dni) {
        return "";
    }

    // Metodos @Query
    @GetMapping("/alumnosDAW")
    public List<AlumnoList> getAlumnosListDAW() {
        return alumnoService.findAllAlumnoListDAW();
    }

    @GetMapping("/alumnosDAM")
    public List<AlumnoList> getAlumnosListDAM() {
        return alumnoService.findAllAlumnoListDAM();
    }

    @GetMapping("/alumnos/ciclo/{ciclo}")
    public List<AlumnoList> getAllAlumnosListCiclo(@PathVariable("ciclo") String ciclo) {
        return alumnoService.findAllAlumnosListCiclo(ciclo);
    }

    @GetMapping("/alumnos/ciclo/{ciclo}/pais/{pais}")
    public List<AlumnoList> getAllAlumnosListCicloPais(
            @PathVariable("ciclo") String ciclo,
            @PathVariable("pais") String pais) {
        return alumnoService.findAllAlumnosListCicloPais(ciclo, pais);
    }

    // Metodos Spring DAta JPA
    @GetMapping("/alumnoslist/dni/{dni}")
    public List<AlumnoList> getAlumnosListByDni(@PathVariable("dni") String dni) {

        return alumnoService.findAllAlumnosListByDni(dni);
    }

    @GetMapping("/alumnoslist/ciclo/{ciclo}")
    public List<AlumnoList> getAlumnosListByCiclo(@PathVariable("ciclo") String ciclo) {
        return alumnoService.findAllAlumnosListByCiclo(ciclo);
    }

    @GetMapping("/alumnoslist/horario/{horario}")
    public List<AlumnoList> getAlumnosListByHorario(@PathVariable("horario") String horario) {
        return alumnoService.findAllAlumnosListByHorario(horario);
    }

    @GetMapping("/alumnoslist/edad/{edad}")
    public List<AlumnoList> getAlumnosListByEdad(@PathVariable("edad") Integer edad) {
        return alumnoService.findAllAlumnosListByEdad(edad);
    }

    @GetMapping("/alumnoslist/cicloandpais/{ciclo}/{pais}")
    public List<AlumnoList> getAlumnosListByCicloAndPais(@PathVariable("ciclo") String ciclo,
            @PathVariable("pais") String pais) {
        return alumnoService.findAlumnosListByCicloAndPais(ciclo, pais);
    }

    @GetMapping("/alumnoslist/distinctbyciclo/{nombre}")
    public List<AlumnoList> getAlumnosListByDistinctbyCiclo(@PathVariable("nombre") String nombre,
            @PathVariable("ciclo") String ciclo) {
        return alumnoService.findAlumnosListByDistintoCiclo(nombre, ciclo);
    }

    @GetMapping("/alumnoslist/nombreignorecase/{nombre}")
    public List<AlumnoList> getAlumnosListByNombreIgnoreCase(@PathVariable("nombre") String nombre) {
        return alumnoService.findAlumnosListByNombreIgnoreCase(nombre);
    }

    @GetMapping("/alumnoslist/cicloandpaisallignorecase/{ciclo}/{pais}")
    public List<AlumnoList> getAlumnosListByCicloAndPaisAllIgnoreCase(@PathVariable("ciclo") String ciclo,
            @PathVariable("pais") String pais) {
        return alumnoService.findAlumnosListByCicloAndPaisAllIgnoreCase(ciclo, pais);
    }

    @GetMapping("/alumnoslist/nombrelike/{nombre}")
    public List<AlumnoList> getAlumnosListByNombreLike(@PathVariable("nombre") String nombre) {
        return alumnoService.findAlumnosListByNombreLike("%" + nombre + "%");

    }


    //ordenacion de resultados
    @GetMapping("/alumnos/ciclo/{ciclo}/OrderByCurso")
    public List<AlumnoList> getAlumnoListCicloOrderByCurso(@PathVariable("ciclo") String ciclo) {
        return alumnoService.findAllAlumnosListCicloOrderByCurso(ciclo);
    }

    //Metodos con Ordenación SQL en Spring Data JPA 
    @GetMapping("/alumnosList/ciclo/{ciclo}/OrderBy/{atributoOrden}/{direccion}")
    public List<AlumnoList> getAlumnoListByCicloOrderBy(@PathVariable ("ciclo") String ciclo, @PathVariable("atributoOrden") String atributoOrden, @PathVariable("direccion") String direccion) {
        if (direccion.isEmpty()) 
            direccion="ASC";
        return alumnoService.findByCicloOrderBy(ciclo,Sort.by(Direction.fromString(direccion),atributoOrden));
        
        
    }
    
    



}