package org.alumne.helena.primer_app_spring_boot.srv.mapper;

import java.util.List;

import org.alumne.helena.primer_app_boot.model.ram.Alumno;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoEdit;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoInfo;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {
	AlumnoMapper INSTANCE= Mappers.getMapper(AlumnoMapper.class);
	
	AlumnoEdit alumnoToAlumnoEdit(Alumno alumno);
	//Devuelve un objeto de tipo 'AlumnoList' a partir de un objeto de tipo 'Alumno'
	AlumnoList alumnoToAlumnoList(Alumno alumno);
	//Devuelve una lista de objetos 'AlumnoList' a partir de una lista de tipo 'Alumno'
	List<AlumnoList> alumnosToAlumnosList(List<Alumno> alumnos);
	
	
	@Mapping(source="dni",target="dni_alumno")
	@Mapping(source="nombre",target="nombre_alumno")
	@Mapping(source="ciclo",target="ciclo_alumno")
	@Mapping(source="curso",target="curso_alumno")
	@Mapping(source="edad",target="edad_alumno")
    	//Devuelve un objeto de tipo 'AlumnoInfo' a partir de un objeto de tipo 'Alumno'
	AlumnoInfo alumnoToAlumnoInfo(Alumno alumno);
    	//Devuelve un objeto de tipo 'Alumno' a partir de un objeto de tipo 'AlumnoEdit'
	Alumno alumnoEditToAlumno(AlumnoEdit alumnoEdit);
	
	//Actualiza un objeto de tipo 'Alumno' con los datos de un objeto de tipo 'AlumnoEdit'
	void updateAlumnoFromAlumnoEdit(AlumnoEdit alumnoEdit,@MappingTarget Alumno alumno);
}

