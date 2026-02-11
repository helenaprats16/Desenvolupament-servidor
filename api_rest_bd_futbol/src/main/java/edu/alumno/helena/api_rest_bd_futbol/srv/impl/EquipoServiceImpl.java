package edu.alumno.helena.api_rest_bd_futbol.srv.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_futbol.model.db.EquipoDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EquipoList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.repository.EquipoRepository;
import edu.alumno.helena.api_rest_bd_futbol.srv.CiudadService;
import edu.alumno.helena.api_rest_bd_futbol.srv.EquipoService;
import edu.alumno.helena.api_rest_bd_futbol.srv.mapper.EquipoMapper;
import io.micrometer.common.lang.NonNull;

@Service
public class EquipoServiceImpl implements EquipoService{

    private final EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository){
        this.equipoRepository = equipoRepository;
    }


    @Override
    public PaginaDto<EquipoList> findAll(@NonNull Pageable paging) {
       Page<EquipoDb> paginaEquipoDb = equipoRepository.findAll(paging);
        return new PaginaDto<EquipoList>(
                paginaEquipoDb.getNumber(),
                paginaEquipoDb.getSize(),
                paginaEquipoDb.getTotalElements(),
                paginaEquipoDb.getTotalPages(),
                EquipoMapper.INSTANCE.equiposDbToEquiposList(paginaEquipoDb.getContent()),
                paginaEquipoDb.getSort());
    }


    

    




}