package org.simarro.alumno.helena.instituto.srv;

import java.util.List;

import org.simarro.alumno.helena.instituto.filters.FiltroException;
import org.simarro.alumno.helena.instituto.filters.model.PaginaResponse;
import org.simarro.alumno.helena.instituto.filters.model.PeticionListadoFiltrado;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoCountByNicknameResponse;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoEdit;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoList;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoResponse;
import org.simarro.alumno.helena.instituto.responseHelpers.BindingResultException;
import org.simarro.alumno.helena.instituto.responseHelpers.EntityAlreadyExistsException;
import org.simarro.alumno.helena.instituto.responseHelpers.EntityNotFoundException;
import org.simarro.alumno.helena.instituto.responseHelpers.MultipartProcessingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

public interface DocAlumnoService {

    /**
     * Crea un nuevo documento en el sistema para un alumno.
     * 
     * @param docAlumnoEdit El objeto DTO que contiene los datos del documento a crear.
     * @return El objeto DTO del documento creado.
     * @throws HttpMessageNotReadableException Si no coincide el tipo de dato de algún atributo y no se puede parsear.
     * @throws BindingResultException Si hay errores de validación en el objeto docAlumnoEdit.
     * @throws EntityAlreadyExistsException Si ya existe un documento con el mismo identificador.
     * @throws DataIntegrityViolationException Si hay errores al almacenar el registro en la BD (clave ajena, restricción de unicidad, integridad de datos).
     * @throws MultipartProcessingException Si hay un problema al procesar el archivo multipart.
     */
    DocAlumnoResponse create(DocAlumnoEdit docAlumnoEdit);

    /**
     * Lee un documento del sistema por su ID.
     * 
     * @param id El identificador único del documento a leer.
     * @return El objeto DTO del documento encontrado.
     * @throws EntityNotFoundException Si no se encuentra un documento con el ID especificado.
     */
    DocAlumnoResponse read(Long id);

    /**
     * Actualiza un documento existente en el sistema.
     * 
     * @param id El identificador único del documento a actualizar.
     * @param docAlumnoEdit El objeto DTO con los nuevos datos del documento.
     * @return El objeto DTO del documento actualizado.
     * @throws HttpMessageNotReadableException Si no coincide el tipo de dato de algún atributo y no se puede parsear.
     * @throws BindingResultException Si hay errores de validación en el objeto docAlumnoEdit.
     * @throws EntityNotFoundException Si no se encuentra un documento con el ID especificado.
     * @throws DataIntegrityViolationException Si hay errores al almacenar el registro en la BD (clave ajena, restricción de unicidad, integridad de datos).
     */
    DocAlumnoResponse update(Long id, DocAlumnoEdit docAlumnoEdit);

    /**
     * Elimina un documento del sistema.
     * 
     * @param id El identificador único del documento a eliminar.
     */
    void delete(Long id);

    /**
     * Obtiene una lista paginada de documentos aplicando filtros y ordenación.
     * 
     * @param filter Filtros de búsqueda en formato `campo:operador:valor` (Ej: "nombreFichero:contiene:Document").
     * @param page Número de página a recuperar (por defecto 0).
     * @param size Cantidad de elementos por página (por defecto 3).
     * @param sort Ordenación en formato `campo,direccion` (Ej: "nombreFichero,asc").
     * @return Una respuesta con la lista paginada de documentos en formato DocAlumnoList.
     * @throws FiltroException Si ocurre un error en la aplicación de filtros.
     */
    PaginaResponse<DocAlumnoList> findAll(String[] filter, int page, int size, String[] sort) throws FiltroException;

    /**
     * Obtiene una lista paginada de documentos mediante un objeto de filtros.
     * 
     * @param peticionListadoFiltrado Objeto que contiene los filtros de búsqueda y opciones de paginación.
     * @return Una respuesta con la lista paginada de documentos en formato DocAlumnoList.
     * @throws FiltroException Si ocurre un error en la aplicación de filtros.
     */
    PaginaResponse<DocAlumnoList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException;


    /**
     * Previsualiza el archivo subido en DocAlumno almacenado como una cadena Base64 en el campo base64Documento.
     * 
     * @param id El identificador único del documento cuyo archivo se desea previsualizar.
     * @return Una respuesta HTTP con el contenido del archivo en formato de bytes, incluyendo headers para 
     *         la correcta visualización en el navegador si es posible.
     * @throws EntityNotFoundException Si no se encuentra un documento con el ID especificado.
     */
    ResponseEntity<byte[]> getDocumentForPreview(Long id);

    /**
     * Obtiene el número de documentos agrupados por el nickname del creador.
     * 
     * @return Una lista de objetos que representan el número de documentos creados por cada nickname.
     */
    List<DocAlumnoCountByNicknameResponse> getDocumentCountByNickname();
}