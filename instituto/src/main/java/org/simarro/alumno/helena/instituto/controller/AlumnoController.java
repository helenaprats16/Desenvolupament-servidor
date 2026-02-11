package org.simarro.alumno.helena.instituto.controller;

import org.simarro.alumno.helena.instituto.exception.BindingResultErrorsResponse;
import org.simarro.alumno.helena.instituto.exception.BindingResultException;
import org.simarro.alumno.helena.instituto.exception.CustomErrorResponse;
import org.simarro.alumno.helena.instituto.exception.DataValidationException;
import org.simarro.alumno.helena.instituto.exception.EntityAlreadyExistsException;
import org.simarro.alumno.helena.instituto.helper.BindingResultHelper;
import org.simarro.alumno.helena.instituto.model.DniString;
import org.simarro.alumno.helena.instituto.model.dto.AlumnoEdit;
import org.simarro.alumno.helena.instituto.srv.AlumnoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    /**
     * Crea un nuevo alumno en el sistema.
     *
     * @param alumnoEdit    Objeto que contiene los datos del alumno a crear. Debe
     *                      cumplir con las validaciones definidas.
     * @param bindingResult Resultado de las validaciones de los datos
     *                      proporcionados.
     * @return Un objeto AlumnoEdit con los datos creados, junto con un código de
     *         estado HTTP 201 (CREATED).
     * @throws BindingResultException          Si hay errores de validación en el
     *                                         objeto alumnoEdit.
     * @throws EntityAlreadyExistsException    Si ya existe un alumno con el DNI
     *                                         especificado.
     * @throws DataIntegrityViolationException Si hay errores al almacenar el
     *                                         registro en la BD (clave ajena,
     *                                         restricción de unicidad, integridad
     *                                         de datos).
     */
    @Operation(summary = "Crea un nuevo registro de tipo alumno en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created: 'Alumno' creado exitosamente", 
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AlumnoEdit.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: Errores de validación en los datos proporcionados (errorCode='STUDENT_CREATE_VALIDATION','DATA_CONVERSION_ERROR')", 
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BindingResultErrorsResponse.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict: Error al intentar crear un 'Alumno' (errorCodes: 'STUDENT_ALREADY_EXIST', 'FOREIGN_KEY_VIOLATION', 'UNIQUE_CONSTRAINT_VIOLATION', 'DATA_INTEGRITY_VIOLATION')", 
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @PostMapping
    public ResponseEntity<AlumnoEdit> create(@Valid @RequestBody AlumnoEdit alumnoEdit, BindingResult bindingResult) {
        // Comprueba errores de validación y si los hay lanza una BindingResultException
        // con el errorCode
        BindingResultHelper.validateBindingResult(bindingResult, "STUDENT_CREATE_VALIDATION");
        // No hay error de validación y procedemos a crear el nuevo registro
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.create(alumnoEdit));
    }

    /**
     * Obtiene los datos de un alumno a partir de su DNI.
     *
     * @param dni DNI del alumno a buscar.
     * @return Un objeto AlumnoEdit con los datos del alumno, junto con un código de
     *         estado HTTP 200 (OK).
     * @throws EntityNotFoundException Si no se encuentra un alumno con el DNI
     *                                 especificado.
     * @throws DataValidationException Si el formato del DNI es incorrecto.
     */
    @Operation(summary = "Devuelve los datos de un alumno dado su DNI.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Alumno encontrado con éxito", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AlumnoEdit.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: Error de validación en el DNI proporcionado (errorCode='DNI_FORMAT_INVALID')", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found: No se encontró el alumno con el DNI proporcionado (errorCode='STUDENT_NOT_FOUND')", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @GetMapping("/{dni}")
    public ResponseEntity<AlumnoEdit> read(@PathVariable String dni) {
        return ResponseEntity.ok(alumnoService.read(new DniString(dni).getValue()));
    }

    /**
     * Actualiza los datos de un alumno existente.
     *
     * @param dni           DNI del alumno a actualizar.
     * @param alumnoEdit    Objeto que contiene los datos actualizados del alumno.
     * @param bindingResult Resultado de las validaciones de los datos
     *                      proporcionados.
     * @return Un objeto AlumnoEdit con los datos actualizados, junto con un código
     *         de estado HTTP 200 (OK).
     * 
     * @throws HttpMessageNotReadableException Si no coincide el tipo de dado de 
     *                                         algn atributo y no se puede parsear
     * @throws BindingResultException          Si hay errores de validación en el
     *                                         objeto alumnoEdit.
     * @throws DataValidationException         Si el formato del DNI es incorrecto.
     * @throws EntityIllegalArgumentException  Si el DNI especificado no coincide
     *                                         con el del alumno a actualizar.
     * @throws EntityNotFoundException         Si no se encuentra un alumno con el
     *                                         DNI especificado.
     * @throws DataIntegrityViolationException Si hay errores al almacenar el
     *                                         registro en la BD (clave ajena,
     *                                         restricción de unicidad, integridad
     *                                         de datos).
     */
    @Operation(summary = "Actualiza los datos de un alumno existente en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Alumno actualizado con éxito", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AlumnoEdit.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: Errores de validación en los datos proporcionados (errorCode='STUDENT_UPDATE_VALIDATION')", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BindingResultErrorsResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: Errores de validación en el DNI proporcionado (errorCodes='DATA_CONVERSION_ERROR', 'DNI_FORMAT_INVALID','STUDENT_DNI_MISMATCH')", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found: No se encontró el alumno con el DNI proporcionado (errorCode='STUDENT_NOT_FOUND_FOR_UPDATE')", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict: Error al intentar actualizar un 'Alumno' (errorCodes: 'FOREIGN_KEY_VIOLATION', 'UNIQUE_CONSTRAINT_VIOLATION', 'DATA_INTEGRITY_VIOLATION')", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @PutMapping("/{dni}")
    public ResponseEntity<AlumnoEdit> update(@PathVariable String dni, @Valid @RequestBody AlumnoEdit alumnoEdit,
            BindingResult bindingResult) {
        // Comprueba errores de validación y si los hay lanza una BindingResultException
        // con el errorCode
        BindingResultHelper.validateBindingResult(bindingResult, "STUDENT_UPDATE_VALIDATION");
        // No hay error de validación y procedemos a modificar el registro
        return ResponseEntity.ok(alumnoService.update(new DniString(dni).getValue(), alumnoEdit));
    }

    /**
     * Elimina un alumno del sistema.
     *
     * @param dni DNI del alumno a eliminar.
     * @return Un código de estado HTTP 204 (NO CONTENT) si la operación es exitosa.
     * @throws DataValidationException Si el formato del DNI es incorrecto.
     */
    @Operation(summary = "Elimina un registro de alumno del sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content: Alumno eliminado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request: Error de validación en el DNI proporcionado (errorCode='DNI_FORMAT_INVALID')", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> delete(@PathVariable String dni) {
        alumnoService.delete(new DniString(dni).getValue());
        return ResponseEntity.noContent().build();
    }
}
