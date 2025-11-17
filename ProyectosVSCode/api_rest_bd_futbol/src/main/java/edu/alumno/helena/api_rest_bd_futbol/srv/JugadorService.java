package edu.alumno.helena.api_rest_bd_futbol.srv;

import org.springframework.data.domain.Pageable;

import edu.alumno.helena.api_rest_bd_futbol.model.dto.JugadorList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;

public interface JugadorService{
    public PaginaDto<JugadorList> findAll(Pageable pageable);
}