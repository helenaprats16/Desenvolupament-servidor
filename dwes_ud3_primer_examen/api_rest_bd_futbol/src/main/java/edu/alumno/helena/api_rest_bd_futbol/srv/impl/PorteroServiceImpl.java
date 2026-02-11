package edu.alumno.helena.api_rest_bd_futbol.srv.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_futbol.model.db.PorteroDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PorteroList;
import edu.alumno.helena.api_rest_bd_futbol.repository.PorteroRepository;
import edu.alumno.helena.api_rest_bd_futbol.srv.PorteroService;
import edu.alumno.helena.api_rest_bd_futbol.srv.mapper.PorteroMapper;
import io.micrometer.common.lang.NonNull;

@Service
public class PorteroServiceImpl implements PorteroService{

    private final PorteroRepository porteroRepository;

    public PorteroServiceImpl(PorteroRepository porteroRepository){
        this.porteroRepository = porteroRepository;
    }


    @Override
    public PaginaDto<PorteroList> findAll(@NonNull Pageable paging) {
       Page<PorteroDb> paginaPorteroDb = porteroRepository.findAll(paging);
        return new PaginaDto<PorteroList>(
                paginaPorteroDb.getNumber(),
                paginaPorteroDb.getSize(),
                paginaPorteroDb.getTotalElements(),
                paginaPorteroDb.getTotalPages(),
                PorteroMapper.INSTANCE.porterosDbToPorterosList(paginaPorteroDb.getContent()),
                paginaPorteroDb.getSort());
    }

    




}