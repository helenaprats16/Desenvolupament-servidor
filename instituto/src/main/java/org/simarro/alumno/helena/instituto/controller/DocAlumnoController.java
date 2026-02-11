package org.simarro.alumno.helena.instituto.controller;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.simarro.alumno.helena.instituto.filters.FiltroException;
import org.simarro.alumno.helena.instituto.filters.model.PaginaResponse;
import org.simarro.alumno.helena.instituto.filters.model.PeticionListadoFiltrado;
import org.simarro.alumno.helena.instituto.helper.BindingResultHelper;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoCountByNicknameResponse;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoEdit;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoList;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoResponse;
import org.simarro.alumno.helena.instituto.responseHelpers.BindingResultErrorsResponse;
import org.simarro.alumno.helena.instituto.responseHelpers.BindingResultException;
import org.simarro.alumno.helena.instituto.responseHelpers.CustomErrorResponse;
import org.simarro.alumno.helena.instituto.responseHelpers.DataValidationException;
import org.simarro.alumno.helena.instituto.responseHelpers.EntityAlreadyExistsException;
import org.simarro.alumno.helena.instituto.responseHelpers.EntityNotFoundException;
import org.simarro.alumno.helena.instituto.responseHelpers.MultipartProcessingException;
import org.simarro.alumno.helena.instituto.srv.DocAlumnoService;
import org.simarro.alumno.helena.instituto.model.IdEntityLong;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/docs")
public class DocAlumnoController {

        private final DocAlumnoService docAlumnoService;

        /**
         * Constructor para inyectar el servicio de documentos de alumnos.
         *
         * @param docAlumnoService Servicio que maneja la lógica de negocio de los
         *                         documentos de alumnos.
         */
        public DocAlumnoController(DocAlumnoService docAlumnoService) {
                this.docAlumnoService = docAlumnoService;
        }

        /**
         * Crea un nuevo documento en el sistema para un alumno.
         *
         * @param docAlumnoEdit Objeto que contiene los datos del documento a crear.
         *                      Debe cumplir con las validaciones definidas.
         * @param bindingResult Resultado de las validaciones de los datos
         *                      proporcionados.
         * @return Un objeto DocAlumnoResponse con los datos creados, junto con un
         *         código de estado HTTP 201 (CREATED).
         * @throws HttpMessageNotReadableException Si no coincide el tipo de dato de
         *                                         algún atributo y no se puede parsear.
         * @throws BindingResultException          Si hay errores de validación en el
         *                                         objeto docAlumnoEdit.
         * @throws EntityAlreadyExistsException    Si ya existe un documento con el
         *                                         mismo identificador.
         * @throws DataIntegrityViolationException Si hay errores al almacenar el
         *                                         registro en la BD (clave ajena,
         *                                         restricción de unicidad, integridad
         *                                         de datos).
         * @throws MultipartProcessingException    Si hay un problema al procesar el
         *                                         archivo multipart.
         */
        @Operation(summary = "Crea un nuevo registro de tipo documento de alumno en el sistema.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Created: 'Documento de alumno' creado exitosamente", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = DocAlumnoEdit.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request: Errores de validación en los datos proporcionados (errorCodes: 'DOC_ALUMNO_CREATE_VALIDATION', 'DATA_CONVERSION_ERROR'.'BAD_MULTIPART')", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = BindingResultErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "409", description = "Conflict: Error al intentar crear un 'Documento de alumno' (errorCodes: 'DOCUMENT_ALREADY_EXIST', 'FOREIGN_KEY_VIOLATION', 'UNIQUE_CONSTRAINT_VIOLATION', 'DATA_INTEGRITY_VIOLATION')", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) })
        })
        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<DocAlumnoResponse> create(@Valid @ModelAttribute DocAlumnoEdit docAlumnoEdit,
                        BindingResult bindingResult) {
                BindingResultHelper.validateBindingResult(bindingResult, "DOC_ALUMNO_CREATE_VALIDATION");
                return ResponseEntity.status(HttpStatus.CREATED).body(docAlumnoService.create(docAlumnoEdit));
        }

        /**
         * Obtiene los datos de un documento de alumno a partir de su ID.
         *
         * @param id ID del documento a buscar.
         * @return Un objeto DocAlumnoResponse con los datos del documento, junto con un
         *         código de estado HTTP 200 (OK).
         * @throws EntityNotFoundException Si no se encuentra un documento con el ID
         *                                 especificado.
         * @throws DataValidationException Si el ID especificado no un valor numérico de
         *                                 tipo Long (INT64).
         *                                 DataValidationException
         */
        @Operation(summary = "Devuelve los datos de un documento de alumno dado su ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK: Documento de alumno encontrado con éxito", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = DocAlumnoEdit.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request: Error de validación en el ID proporcionado (errorCode:'ID_FORMAT_INVALID')", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) }),
                        @ApiResponse(responseCode = "404", description = "Not Found: No se encontró el documento con el ID proporcionado (errorCode: 'STUDENT_DOC_NOT_FOUND')", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) })
        })
        @GetMapping("/{id}")
        public ResponseEntity<DocAlumnoResponse> read(@PathVariable String id) {
                return ResponseEntity.ok(docAlumnoService.read(new IdEntityLong(id).getValue()));
        }

        /**
         * Obtiene la previsualización de un documento de alumno a partir de su ID.
         *
         * @param id ID del documento a buscar.
         * @return Un fichero en formato bytes[] previsualizable en en navegador si el
         *         navegador tiene la aplicación para visualizar ese tipo de fichero
         * @throws EntityNotFoundException Si no se encuentra el documento con el ID
         *                                 especificado.
         * @throws DataValidationException Si el ID especificado no un valor numérico de
         *                                 tipo Long (INT64).
         *                                 DataValidationException
         */
        @Operation(summary = "Devuelve el archivo para previsualización almacenado en la BD con el ID especificado en la documentación del alumno.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK: Archivo devuelto con éxito para previsualización", 
                content = @Content(
                        mediaType = "application/octet-stream", // Esto es un ejemplo, el tipo MIME debería ser dinámico basado en el archivo
                        schema = @Schema(type = "string", format = "binary") // Indica que se trata de datos binarios
                )
        ),
        @ApiResponse(responseCode = "400", description = "Bad Request: Error de validación en el ID proporcionado (errorCode:'ID_FORMAT_INVALID')", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Not Found: No se encontró el documento con el ID proporcionado (errorCode: 'STUDENT_DOC_NOT_FOUND')", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) })
})
@GetMapping("/preview/{id}")
        public ResponseEntity<byte[]> previewDocument(@PathVariable String id) {
                return docAlumnoService.getDocumentForPreview(new IdEntityLong(id).getValue());
        }

        /**
         * Actualiza los datos de un documento de alumno existente.
         *
         * @param id            ID del documento a actualizar.
         * @param docAlumnoEdit Objeto que contiene los datos actualizados del
         *                      documento.
         * @param bindingResult Resultado de las validaciones de los datos
         *                      proporcionados.
         * @return Un objeto DocAlumnoResponse con los datos actualizados, junto con un
         *         código de estado HTTP 200 (OK).
         * @throws HttpMessageNotReadableException Si no coincide el tipo de dato de
         *                                         algún atributo y no se puede parsear.
         * @throws DataValidationException         Si el ID especificado no un valor
         *                                         numérico de tipo Long (INT64).
         * @throws BindingResultException          Si hay errores de validación en el
         *                                         objeto docAlumnoEdit.
         * @throws EntityNotFoundException         Si no se encuentra un documento con
         *                                         el ID especificado.
         * @throws DataIntegrityViolationException Si hay errores al almacenar el
         *                                         registro en la BD (clave ajena,
         *                                         restricción de unicidad, integridad
         *                                         de datos).
         */
        @Operation(summary = "Actualiza los datos de un documento de alumno existente en el sistema.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK: Documento de alumno actualizado con éxito", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = DocAlumnoEdit.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request: Errores de validación en los datos proporcionados (errorCode: 'DOC_ALUMNO_UPDATE_VALIDATION')", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = BindingResultErrorsResponse.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request: Error de validación en el ID proporcionado (errorCode:'ID_FORMAT_INVALID')", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) }),
                        @ApiResponse(responseCode = "404", description = "Not Found: No se encontró el documento con el ID proporcionado (errorCode: 'STUDENT_DOC_NOT_FOUND_FOR_UPDATE')", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) }),
                        @ApiResponse(responseCode = "409", description = "Conflict: Error al intentar actualizar un 'Documento de alumno' (errorCodes: 'FOREIGN_KEY_VIOLATION', 'UNIQUE_CONSTRAINT_VIOLATION', 'DATA_INTEGRITY_VIOLATION')", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) })
        })
        @PutMapping("/{id}")
        public ResponseEntity<DocAlumnoResponse> update(@PathVariable String id,
                        @Valid @ModelAttribute DocAlumnoEdit docAlumnoEdit, BindingResult bindingResult) {
                BindingResultHelper.validateBindingResult(bindingResult, "DOC_ALUMNO_UPDATE_VALIDATION");
                return ResponseEntity.ok(docAlumnoService.update(new IdEntityLong(id).getValue(), docAlumnoEdit));
        }

        /**
         * Elimina un documento de alumno del sistema.
         *
         * @param id ID del documento a eliminar.
         * @return Un código de estado HTTP 204 (NO CONTENT) si la operación es exitosa.
         * @throws DataValidationException Si el ID especificado no es un valor numérico 
         *                                 de tipo Long (INT64).
         */
        @Operation(summary = "Elimina un registro de documento de alumno del sistema.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "No Content: Documento de alumno eliminado", content = @Content),
                        @ApiResponse(responseCode = "400", description = "Bad Request: Error de validación en el ID proporcionado (errorCode:'ID_FORMAT_INVALID')", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) })
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable String id) {
                docAlumnoService.delete(new IdEntityLong(id).getValue());
                return ResponseEntity.noContent().build();
        }

        /**
         * Obtiene una lista paginada de documentos con opción de filtrado y ordenación.
         *
         * @param filter Opcional. Filtros de búsqueda en formato `campo:operador:valor`
         *               (Ej: "nombreFichero:contiene:Document").
         * @param page   Número de página (por defecto 0).
         * @param size   Cantidad de elementos por página (por defecto 3).
         * @param sort   Ordenación en formato `campo,direccion` (Ej:
         *               "nombreFichero,asc").
         * @return Una respuesta con la lista paginada de documentos.
         * @throws FiltroException Si ocurre un error en la aplicación de filtros.
         */
        @Operation(summary = "Obtener documentos paginados", description = "Retorna una lista paginada de documentos aplicando filtros y ordenación opcionales.")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Lista de documentos obtenida correctamente", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PaginaResponse.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request: Errores de filtrado u ordenación (errorCodes: 'BAD_OPERATOR_FILTER','BAD_ATTRIBUTE_ORDER','BAD_ATTRIBUTE_FILTER','BAD_FILTER'", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) }),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        @GetMapping("/search")
        public ResponseEntity<PaginaResponse<DocAlumnoList>> getAllDocsGET(
                        @RequestParam(required = false) String[] filter,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "3") int size,
                        @RequestParam(defaultValue = "id,asc") String[] sort) throws FiltroException {
                return ResponseEntity.ok(docAlumnoService.findAll(filter, page, size, sort));
        }

        /**
         * Obtiene una lista paginada de documentos mediante una solicitud POST con un
         * objeto de filtros.
         *
         * @param peticionListadoFiltrado Objeto con los filtros de búsqueda y opciones
         *                                de paginación.
         * @return Una respuesta con la lista paginada de documentos.
         * @throws FiltroException Si ocurre un error en la aplicación de filtros.
         */
        @Operation(summary = "Obtener documentos con filtros avanzados", description = "Retorna documentos paginados enviando los filtros en el cuerpo de la petición.")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Lista de documentos obtenida correctamente", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PaginaResponse.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request: Errores de filtrado u ordenación (errorCodes: 'BAD_OPERATOR_FILTER','BAD_ATTRIBUTE_ORDER','BAD_ATTRIBUTE_FILTER','BAD_FILTER')", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)) }),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        @PostMapping("/search")
        public ResponseEntity<PaginaResponse<DocAlumnoList>> getAllDocsPOST(
                        @Valid @RequestBody PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException {
                return ResponseEntity.ok(docAlumnoService.findAll(peticionListadoFiltrado));
        }

        /**
         * Obtiene el número de documentos agrupados por el nickname del creador.
         *
         * @return Una lista de objetos que representan el número de documentos creados
         *         por cada nickname.
         */
        @Operation(summary = "Obtener contador de documentos por nickname", description = "Retorna una lista agrupada con el número de documentos para cada nickname.")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Lista agrupada de documentos por nickname", 
                                content = {
                                        @Content(mediaType = "application/json", 
                                                schema = @Schema(
                                                        type = "array", 
                                                        implementation = DocAlumnoCountByNicknameResponse.class
                                                )
                                        )
                                }
                        )
        })
        @GetMapping("/count-by-nickname")
        public ResponseEntity<List<DocAlumnoCountByNicknameResponse>> getDocumentCountByNickname() {
                return ResponseEntity.ok(docAlumnoService.getDocumentCountByNickname());
        }
}