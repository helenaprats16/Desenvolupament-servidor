package edu.alumno.helena.api_rest_bd_futbol.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_futbol.model.db.JugadorDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.JugadorList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.repository.JugadorRepository;
import edu.alumno.helena.api_rest_bd_futbol.srv.JugadorService;
import edu.alumno.helena.api_rest_bd_futbol.srv.mapper.JugadorMapper;
import io.micrometer.common.lang.NonNull;

@Service
public class JugadorServiceImpl implements JugadorService{

    private final JugadorRepository jugadorRepository;

    public JugadorServiceImpl(JugadorRepository jugadorRepository){
        this.jugadorRepository = jugadorRepository;
    }


    @Override
    public PaginaDto<JugadorList> findAll(@NonNull Pageable paging) {
       Page<JugadorDb> paginaJugadorDb = jugadorRepository.findAll(paging);
        return new PaginaDto<JugadorList>(
                paginaJugadorDb.getNumber(),
                paginaJugadorDb.getSize(),
                paginaJugadorDb.getTotalElements(),
                paginaJugadorDb.getTotalPages(),
                JugadorMapper.INSTANCE.jugadoresDbtoJugadoresList(paginaJugadorDb.getContent()),
                paginaJugadorDb.getSort());
    }

    




}