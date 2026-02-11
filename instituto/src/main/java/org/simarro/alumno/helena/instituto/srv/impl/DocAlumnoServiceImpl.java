package org.simarro.alumno.helena.instituto.srv.impl;

import java.util.List;

import org.simarro.alumno.helena.instituto.filters.FiltroException;
import org.simarro.alumno.helena.instituto.filters.model.PaginaResponse;
import org.simarro.alumno.helena.instituto.filters.model.PeticionListadoFiltrado;
import org.simarro.alumno.helena.instituto.filters.specification.FiltroBusquedaSpecification;
import org.simarro.alumno.helena.instituto.model.db.DocAlumnoDb;
import org.simarro.alumno.helena.instituto.model.db.DocAlumnoEditDb;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoCountByNicknameResponse;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoEdit;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoList;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoResponse;
import org.simarro.alumno.helena.instituto.srv.DocAlumnoService;
import org.simarro.alumno.helena.instituto.srv.FileDownloadService;
import org.simarro.alumno.helena.instituto.srv.mapper.DocAlumnoMapper;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.simarro.alumno.helena.instituto.filters.utils.PaginationFactory;
import org.simarro.alumno.helena.instituto.filters.utils.PeticionListadoFiltradoConverter;
import org.simarro.alumno.helena.instituto.repository.DocAlumnoCrudRepository;
import org.simarro.alumno.helena.instituto.repository.DocAlumnoRepository;
import org.simarro.alumno.helena.instituto.responseHelpers.EntityIllegalArgumentException;
import org.simarro.alumno.helena.instituto.responseHelpers.EntityNotFoundException;
import org.simarro.alumno.helena.instituto.responseHelpers.MultipartProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class DocAlumnoServiceImpl implements DocAlumnoService {

    private final DocAlumnoRepository docAlumnoRepository;
    private final DocAlumnoCrudRepository docAlumnoCrudRepository;
    private final FileDownloadService fileDownloadService;
    private final PaginationFactory paginationFactory;
    private final PeticionListadoFiltradoConverter peticionConverter;

    public DocAlumnoServiceImpl(DocAlumnoRepository docAlumnoRepository,
                                DocAlumnoCrudRepository docAlumnoCrudRepository,
                                FileDownloadService fileDownloadService,
                                PaginationFactory paginationFactory,
                                PeticionListadoFiltradoConverter peticionConverter) {
        this.docAlumnoRepository = docAlumnoRepository;
        this.docAlumnoCrudRepository = docAlumnoCrudRepository;
        this.fileDownloadService=fileDownloadService;
        this.paginationFactory = paginationFactory;
        this.peticionConverter = peticionConverter;
    }

    /**
     * CRUD 
     */

    @Override
    public DocAlumnoResponse create(DocAlumnoEdit docAlumnoEdit) {
        // Validar que dni y multipart no sean nulos
        if (docAlumnoEdit.getMultipart() == null || docAlumnoEdit.getMultipart().isEmpty()) {
            throw new MultipartProcessingException("BAD_MULTIPART", "El archivo no puede estar vacío");
        }
         if (docAlumnoEdit.getId() != null) { //Como el id lo crea la BD no debemos pasarle un valor inicial
            throw  new EntityIllegalArgumentException("STUDENT_DOC_ID_MISMATCH",
            "El ID debe ser nulo al crear un nuevo documento.", null);
        }

        // Mapear y guardar
        DocAlumnoEditDb entity = DocAlumnoMapper.INSTANCE.docAlumnoEditToDocAlumnoEditDb(docAlumnoEdit);
        DocAlumnoEditDb savedEntity = docAlumnoCrudRepository.save(entity);

        // Devolver el DTO mapeado
        return DocAlumnoMapper.INSTANCE.docAlumnoEditDbToDocAlumnoResponse(savedEntity);
    }
    
    

    @Override
    public DocAlumnoResponse read(Long id) {
        DocAlumnoEditDb entity = docAlumnoCrudRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("STUDENT_DOC_NOT_FOUND"));
        return DocAlumnoMapper.INSTANCE.docAlumnoEditDbToDocAlumnoResponse(entity);
    }


    @Override
    public ResponseEntity<byte[]> getDocumentForPreview(Long id) {
        DocAlumnoEditDb doc = docAlumnoCrudRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("STUDENT_DOC_NOT_FOUND", "No se encontró el documento con ID " + id));
        
        return fileDownloadService.prepareDownloadResponse(
            doc.getBase64Documento(), 
            doc.getContentTypeDocumento(), 
            doc.getNombreFichero()
        );
    }

    @Override
    public DocAlumnoResponse update(Long id, DocAlumnoEdit docAlumnoEdit) {
        DocAlumnoEditDb existingEntity = docAlumnoCrudRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("STUDENT_DOC_NOT_FOUND", "No se puede actualizar. El documento con ID " + id + " no existe."));
        
                DocAlumnoMapper.INSTANCE.updateDocAlumnoEditDbFromDocAlumnoEdit(docAlumnoEdit, existingEntity);
        return DocAlumnoMapper.INSTANCE.docAlumnoEditDbToDocAlumnoResponse(docAlumnoCrudRepository.save(existingEntity));
    }

    @Override
    public void delete(Long id) {
        if (docAlumnoCrudRepository.existsById(id)) {
            docAlumnoCrudRepository.deleteById(id);
        }
    }

    /**
     * LISTADOS
     */

    @Override
    public PaginaResponse<DocAlumnoList> findAll(String[] filter, int page, int size, String[] sort) 
            throws FiltroException {
        /** 'peticionConverter' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        PeticionListadoFiltrado peticion = peticionConverter.convertFromParams(filter, page, size, sort);
        return findAll(peticion);
    }

    @SuppressWarnings("null")
    @Override
    public PaginaResponse<DocAlumnoList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) {
        /** 'paginationFactory' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        try {
            Pageable pageable = paginationFactory.createPageable(peticionListadoFiltrado);
            // Configurar criterio de filtrado con Specification
            Specification<DocAlumnoDb> filtrosBusquedaSpecification = new FiltroBusquedaSpecification<DocAlumnoDb>(
                peticionListadoFiltrado.getListaFiltros());
            // Filtrar y ordenar: puede producir cualquier de los errores controlados en el catch
            Page<DocAlumnoDb> page = docAlumnoRepository.findAll(filtrosBusquedaSpecification, pageable);
            //Devolver respuesta
            return DocAlumnoMapper.pageToPaginaResponseAlumnoList(
                page,
                peticionListadoFiltrado.getListaFiltros(), 
                peticionListadoFiltrado.getSort());
        } catch (JpaSystemException e) {
            String cause="";
            if  (e.getRootCause()!=null){
                if (e.getCause().getMessage()!=null)
                    cause= e.getRootCause().getMessage();
            }
            throw new FiltroException("BAD_OPERATOR_FILTER",
                    "Error: No se puede realizar esa operación sobre el atributo por el tipo de dato", e.getMessage()+":"+cause);
        } catch (PropertyReferenceException e) {
            throw new FiltroException("BAD_ATTRIBUTE_ORDER",
                    "Error: No existe el nombre del atributo de ordenación en la tabla", e.getMessage());
        } catch (InvalidDataAccessApiUsageException e) {
            throw new FiltroException("BAD_ATTRIBUTE_FILTER", "Error: Posiblemente no existe el atributo en la tabla",
                    e.getMessage());
        }
    }

    // Consulta de agrupación
    @Override
    public List<DocAlumnoCountByNicknameResponse> getDocumentCountByNickname() {
        List<Object[]> results = docAlumnoRepository.countDocumentsGroupedByCreador();
        return DocAlumnoMapper.toDocAlumnoCountByNickname(results);
    }
}