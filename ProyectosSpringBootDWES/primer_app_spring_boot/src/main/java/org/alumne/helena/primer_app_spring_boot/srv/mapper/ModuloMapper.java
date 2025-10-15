package org.alumne.helena.primer_app_spring_boot.srv.mapper;

import java.util.List;

import org.alumne.helena.primer_app_boot.model.ram.Alumno;
import org.alumne.helena.primer_app_boot.model.ram.Modulo;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoEdit;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoInfo;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoList;
import org.alumne.helena.primer_app_spring_boot.model.dto.ModuloEdit;
import org.alumne.helena.primer_app_spring_boot.model.dto.ModuloList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ModuloMapper {
	ModuloMapper INSTANCE= Mappers.getMapper(ModuloMapper.class);
	
	Modulo moduloEditToModulo(ModuloEdit moduloEdit);
	ModuloList moduloToModuloList(ModuloList moduloList);
	List<ModuloList> moduloToLisModuloList(List<Modulo> modulos);
}

