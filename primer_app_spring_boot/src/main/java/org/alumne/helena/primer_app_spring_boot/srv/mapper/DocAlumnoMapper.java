package org.alumne.helena.primer_app_spring_boot.srv.mapper;

import java.util.List;

import org.alumne.helena.primer_app_boot.model.ram.DocAlumno;
import org.alumne.helena.primer_app_spring_boot.model.dto.DocAlumnoEdit;
import org.alumne.helena.primer_app_spring_boot.model.dto.DocAlumnoList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocAlumnoMapper {
DocAlumnoMapper INSTANCE= Mappers.getMapper(DocAlumnoMapper.class);
	
	//Devuelve un objeto de tipo 'DocAlumnoEdit' a partir de un objeto de tipo 'DocAlumno'
	DocAlumnoEdit docAlumnoToDocAlumnoEdit(DocAlumno docAlumno);
	//Devuelve una lista de objetos 'DocAlumnoList' a partir de una lista de tipo 'DocAlumno'
	List<DocAlumnoList> docsAlumnoToDocsAlumnoList(List<DocAlumno> docAlumnos);
	//Devuelve un objeto de tipo 'DocAlumno' a partir de un objeto de tipo 'DocAlumnoEdit'
	DocAlumno docAlumnoEditToDocAlumno(DocAlumnoEdit docAlumnoEdit);
}

