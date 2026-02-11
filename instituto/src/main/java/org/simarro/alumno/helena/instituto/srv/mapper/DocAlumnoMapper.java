package org.simarro.alumno.helena.instituto.srv.mapper;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.simarro.alumno.helena.instituto.filters.model.FiltroBusqueda;
import org.simarro.alumno.helena.instituto.filters.model.PaginaResponse;
import org.simarro.alumno.helena.instituto.model.db.DocAlumnoDb;
import org.simarro.alumno.helena.instituto.model.db.DocAlumnoEditDb;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoEdit;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoList;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoResponse;
import org.simarro.alumno.helena.instituto.model.dto.DocAlumnoCountByNicknameResponse;
import org.simarro.alumno.helena.instituto.utils.MultipartUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

@SuppressWarnings("unused")
@Mapper(imports = {MultipartUtils.class})
public interface DocAlumnoMapper {
    DocAlumnoMapper INSTANCE = Mappers.getMapper(DocAlumnoMapper.class);
    /**
     * CRUD 
     */
    // Devuelve un objeto de tipo 'DocAlumnoRespomse' a partir de un objeto de tipo 'DocAlumnoEditDb'
    DocAlumnoResponse docAlumnoEditDbToDocAlumnoResponse(DocAlumnoEditDb docAlumnoEditDb);

    // Devuelve un objeto de tipo 'DocAlumnoEditDb' a partir de un objeto de tipo 'DocAlumnoEdit'
    @Mapping(target = "base64Documento", expression = "java(MultipartUtils.multipartToString(docAlumnoEdit.getMultipart()))")
    @Mapping(target = "extensionDocumento", expression = "java(MultipartUtils.getExtensionMultipartfile(docAlumnoEdit.getMultipart()))")
    @Mapping(target = "contentTypeDocumento", expression = "java(docAlumnoEdit.getMultipart().getContentType())")
    DocAlumnoEditDb docAlumnoEditToDocAlumnoEditDb(DocAlumnoEdit docAlumnoEdit);

    // Actualiza un objeto de tipo 'DocAlumnoEditDb' con los datos de un objeto de tipo 'DocAlumnoEdit'
    @Mapping(target = "base64Documento", expression = "java(MultipartUtils.multipartToString(docAlumnoEdit.getMultipart()))")
    @Mapping(target = "extensionDocumento", expression = "java(MultipartUtils.getExtensionMultipartfile(docAlumnoEdit.getMultipart()))")
    @Mapping(target = "contentTypeDocumento", expression = "java(docAlumnoEdit.getMultipart().getContentType())")
    void updateDocAlumnoEditDbFromDocAlumnoEdit(DocAlumnoEdit docAlumnoEdit, @MappingTarget DocAlumnoEditDb docAlumnoDb);

    /**
     * LISTADOS
     */
    // Devuelve una lista de objetos 'DocAlumnoList' a partir de una lista de tipo 'DocAlumnoDb'
    List<DocAlumnoList> docsAlumnoDbToDocsAlumnoList(List<DocAlumnoDb> docAlumnosDb);

    /**
     * Agrupaciones
     */
    // Devuelve una lista de objetos 'DocAlumnoList' a partir de una consulta de agrupación sobre 'DocAlumnoDb'
    static List<DocAlumnoCountByNicknameResponse> toDocAlumnoCountByNickname(List<Object[]> results) {
        return results.stream()
                .map(result -> new DocAlumnoCountByNicknameResponse((String) result[0], (Long) result[1]))
                .collect(Collectors.toList());
    }

    /**
     * Convierte una página de DocAlumnoDb en una respuesta paginada
     */
    static PaginaResponse<DocAlumnoList> pageToPaginaResponseAlumnoList(
            Page<DocAlumnoDb> page,
            List<FiltroBusqueda> filtros,
            List<String> ordenaciones) {
        return new PaginaResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                INSTANCE.docsAlumnoDbToDocsAlumnoList(page.getContent()),
                filtros,
                ordenaciones);
    }
}

