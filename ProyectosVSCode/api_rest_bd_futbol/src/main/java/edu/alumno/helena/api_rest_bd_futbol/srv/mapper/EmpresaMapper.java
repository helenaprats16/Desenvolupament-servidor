package edu.alumno.helena.api_rest_bd_futbol.srv.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import edu.alumno.helena.api_rest_bd_futbol.model.db.EmpresaDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EmpresaInfo;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EmpresaList;

@Mapper(uses= EquipoMapper.class)
public interface EmpresaMapper {
    EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);

    EmpresaList EmpresaDbToEmpresaList(EmpresaDb empresaDb);
 
 
    List<EmpresaList> empresaToEmpresaList(List<EmpresaDb> empresaDb);

    EmpresaInfo empresaToEmpresaInfo(EmpresaDb db);

    // //Cuando tenga el atriburo "equiposDb" de tipo "Set<EquipoDb> lo copie a "equiposInfoNombres convirtiendolo en un "Set<EquipoInfoNombre>"
    // @Mapping(target= "equiposInfoNombres",source= "equiposNombreDb")
    // CiudadInfo ciudadDbToCiudadInfo(CiudadDb ciudadDb);



}
