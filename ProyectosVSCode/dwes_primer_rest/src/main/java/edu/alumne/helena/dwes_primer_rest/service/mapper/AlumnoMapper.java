package edu.alumne.helena.dwes_primer_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import edu.alumne.helena.dwes_primer_rest.model.db.AlumnoDb;
import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoEdit;
import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoInfo;
import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoList;

@Mapper
public interface AlumnoMapper {
	AlumnoMapper INSTANCE= Mappers.getMapper(AlumnoMapper.class);
	
	//Devuelve un objeto de tipo 'AlumnoEdit' a partir de un objeto de tipo 'AlumnoDb'
	AlumnoEdit alumnoDbToAlumnoEdit(AlumnoDb alumnoDb);
	//Devuelve un objeto de tipo 'AlumnoList' a partir de un objeto de tipo 'AlumnoDb'
	AlumnoList alumnoDbToAlumnoList(AlumnoDb alumnoDb);
	//Devuelve una lista de objetos 'AlumnoList' a partir de una lista de tipo 'AlumnoDb'
	
	List<AlumnoList> alumnosToAlumnosList(List<AlumnoDb> alumnosDb);

	@Mapping(source="dni",target="dni_alumno")
	@Mapping(source="nombre",target="nombre_alumno")
	@Mapping(source="ciclo",target="ciclo_alumno")
	@Mapping(source="curso",target="curso_alumno")
    	//Devuelve un objeto de tipo 'AlumnoInfo' a partir de un objeto de tipo 'AlumnoDb'
	AlumnoInfo alumnoDbToAlumnoInfo(AlumnoDb alumnoDb);
    	//Devuelve un objeto de tipo 'AlumnoDb' a partir de un objeto de tipo 'AlumnoEdit'
	AlumnoDb alumnoEditToAlumnoDb(AlumnoEdit alumnoEdit);
	
	//Actualiza un objeto de tipo 'AlumnoDb' con los datos de un objeto de tipo 'AlumnoEdit'
	void updateAlumnoDbFromAlumnoEdit(AlumnoEdit alumnoEdit,@MappingTarget AlumnoDb alumnoDb);
	
}