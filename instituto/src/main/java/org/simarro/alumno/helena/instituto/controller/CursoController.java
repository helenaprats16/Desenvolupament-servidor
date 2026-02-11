package org.simarro.alumno.helena.instituto.controller;

import org.simarro.alumno.helena.instituto.exception.BindingResultErrorsResponse;
import org.simarro.alumno.helena.instituto.exception.BindingResultException;
import org.simarro.alumno.helena.instituto.exception.CustomErrorResponse;
import org.simarro.alumno.helena.instituto.exception.DataValidationException;
import org.simarro.alumno.helena.instituto.exception.EntityNotFoundException;
import org.simarro.alumno.helena.instituto.helper.BindingResultHelper;
import org.simarro.alumno.helena.instituto.model.IdEntityLong;
import org.simarro.alumno.helena.instituto.model.dto.CursoEdit;
import org.simarro.alumno.helena.instituto.srv.CursoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cursos")
public class CursoController {

    private final CursoService cursoService;
    /**
     * Crea un nuevo Curso en el sistema.
     *
     * @param cursoEdit Objeto que contiene los datos del Curso a crear. Debe cumplir con las validaciones definidas.
     * @param bindingResult Resultado de las validaciones de los datos proporcionados.
     * @return Un objeto CursoEdit con los datos creados, junto con un código de estado HTTP 201 (CREATED).
     * @throws HttpMessageNotReadableException Si no coincide el tipo de dado de algUn atributo y no se puede parsear
     * @throws BindingResultException Si hay errores de validación en el objeto CursoEdit.
     * @throws EntityIllegalArgumentException Si el Id no está nulo.
     * @throws DataIntegrityViolationException Si hay errores al almacenar el registro en la BD (clave ajena, restricción de unicidad, integridad de datos).
     */
    @Operation(summary = "Crea un nuevo registro de tipo Curso en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created: 'Curso' creado exitosamente", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CursoEdit.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request: Errores de validación en los datos proporcionados (errorCode='CURSO_CREATE_VALIDATION')", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = BindingResultErrorsResponse.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request: Error porque el Id del Curso debe ser nulo (errorCode='DATA_CONVERSION_ERROR','CURSO_ID_MISMATCH')", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CustomErrorResponse.class)) }),
        @ApiResponse(responseCode = "409", description = "Conflict: Error al intentar crear un 'Curso' (errorCodes: 'FOREIGN_KEY_VIOLATION', 'UNIQUE_CONSTRAINT_VIOLATION', 'DATA_INTEGRITY_VIOLATION')", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @PostMapping
    public ResponseEntity<CursoEdit> create(@Valid @RequestBody CursoEdit cursoEdit, BindingResult bindingResult) {
        //Comprueba errores de validación y si los hay lanza una BindingResultException con el errorCode
        BindingResultHelper.validateBindingResult(bindingResult, "CURSO_CREATE_VALIDATION");
        //No hay error de validación y procedemos a crear el nuevo registro
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.create(cursoEdit));
    }

    /**
     * Obtiene los datos de un Curso a partir de su Id.
     *
     * @param id Id del Curso a buscar.
     * @return Un objeto CursoEdit con los datos del Curso, junto con un código de estado HTTP 200 (OK).
     * * @throws DataValidationException Si el formato del id es incorrecto.
     * @throws EntityNotFoundException Si no se encuentra un Curso con el Id especificado.
     */
    @Operation(summary = "Devuelve los datos de un Curso dado su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK: Curso encontrado con éxito", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CursoEdit.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request: Error de validación en el ID proporcionado (errorCode='ID_FORMAT_INVALID'))", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CustomErrorResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Not Found: No se encontró el Curso con el ID proporcionado (errorCode='CURSO_NOT_FOUND')",  
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CustomErrorResponse.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<CursoEdit> read(@PathVariable String id) {
        return ResponseEntity.ok(cursoService.read(new IdEntityLong(id).getValue()));
    }

    /**
     * Actualiza los datos de un Curso existente.
     *
     * @param id Id del Curso a actualizar.
     * @param cursoEdit Objeto que contiene los datos actualizados del Curso.
     * @param bindingResult Resultado de las validaciones de los datos proporcionados.
     * @return Un objeto CursoEdit con los datos actualizados, junto con un código de estado HTTP 200 (OK).
     * @throws BindingResultException Si hay errores de validación en el objeto CursoEdit.
     * @throws DataValidationException Si el formato del id es incorrecto.
     * @throws EntityIllegalArgumentException Si el id especificado no coincide con el del Curso a actualizar.
     * @throws EntityNotFoundException Si no se encuentra un Curso con el id especificado.
     * @throws DataIntegrityViolationException Si hay errores al almacenar el registro en la BD (clave ajena, restricción de unicidad, integridad de datos).
     */
    @Operation(summary = "Actualiza los datos de un Curso existente en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK: Curso actualizado con éxito", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CursoEdit.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request: Errores de validación en los datos proporcionados (errorCodes='CURSO_UPDATE_VALIDATION')", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = BindingResultErrorsResponse.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request: Error de validación en el ID proporcionado (errorCodes='ID_FORMAT_INVALID','CURSO_ID_MISMATCH'))", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CustomErrorResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Not Found: No se encontró el Curso con el ID proporcionado (errorCode='CURSO_NOT_FOUND')", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CustomErrorResponse.class))}),
        @ApiResponse(responseCode = "409", description = "Conflict: Error al intentar actualizar un 'Curso' (errorCodes: 'FOREIGN_KEY_VIOLATION', 'UNIQUE_CONSTRAINT_VIOLATION', 'DATA_INTEGRITY_VIOLATION')", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @PutMapping("/{id}")
    public ResponseEntity<CursoEdit> update(@PathVariable String id, @Valid @RequestBody CursoEdit cursoEdit, BindingResult bindingResult) {
        //Comprueba errores de validación y si los hay lanza una BindingResultException con el errorCode
        BindingResultHelper.validateBindingResult(bindingResult, "CURSO_UPDATE_VALIDATION");
        //No hay error de validación y procedemos a modificar el registro
        return ResponseEntity.ok(cursoService.update(new IdEntityLong(id).getValue(), cursoEdit));
    }

    /**
     * Elimina un Curso del sistema.
     *
     * @param id Id del Curso a eliminar.
     * @return Un código de estado HTTP 204 (NO CONTENT) si la operación es exitosa.
     * @throws DataValidationException Si el formato del Id es incorrecto.
     */
    @Operation(summary = "Elimina un registro de Curso del sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "No Content: Curso eliminado", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad Request: Error de validación en el ID proporcionado (errorCode='ID_FORMAT_INVALID')", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CustomErrorResponse.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        cursoService.delete(new IdEntityLong(id).getValue());
        return ResponseEntity.noContent().build();
    }
}
