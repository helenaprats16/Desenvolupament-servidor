package edu.alumno.helena.api_rest_bd_futbol.srv;

import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EquipoList;
import org.springframework.data.domain.Pageable;

public interface EquipoService{

    public PaginaDto<EquipoList> findAll(Pageable paging);
    
}