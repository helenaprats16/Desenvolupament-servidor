package edu.alumno.helena.api_rest_bd_futbol.srv;

import org.springframework.data.domain.Pageable;

import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PorteroList;

public interface PorteroService{
    public PaginaDto<PorteroList> findAll(Pageable pageable);
}