package edu.alumno.helena.api_rest_bd_pelicula.srv.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_pelicula.exception.PeliculaNotFoundException;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.PeliculaDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaList;
import edu.alumno.helena.api_rest_bd_pelicula.repository.PeliculaRepository;
import edu.alumno.helena.api_rest_bd_pelicula.srv.PeliculaService;
import edu.alumno.helena.api_rest_bd_pelicula.srv.mapper.PeliculaMapper;
import io.micrometer.common.lang.NonNull;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final PeliculaMapper peliculaMapper;
    
    public PeliculaServiceImpl(PeliculaRepository peliculaRepository, PeliculaMapper peliculaMapper) {
        this.peliculaRepository = peliculaRepository;
        this.peliculaMapper = peliculaMapper;
    }


    @Override
    public List<PeliculaList> findAllPeliculaList() {
        return peliculaMapper.peliculasToPeliculaList(peliculaRepository.findAll());
    }

    @Override
    public List<PeliculaList> findAllPeliculaList(@NonNull Sort sort) {
        return peliculaMapper.peliculasToPeliculaList(peliculaRepository.findAll(sort));
    }

    @Override
    public PeliculaInfo getPeliculaInfoById(@NonNull Long id) {
        PeliculaDb peliculaDb = peliculaRepository.findById(id)
        .orElseThrow(()-> new PeliculaNotFoundException("Pelicula no trabada amb ::"+id,"Pelicula NOT FOUND "));
        return (peliculaMapper.peliculaDbToPeliculaInfo(peliculaDb));

    }

    @Override
    public PaginaDto<PeliculaList> findAllPagePeliculaList(Pageable pagina) {
        Page<PeliculaDb> paginaPeliculaDb = peliculaRepository.findAll(pagina);
        return new PaginaDto<PeliculaList>(
                    paginaPeliculaDb.getNumber(),//numero de pagina solicitada
                    paginaPeliculaDb.getSize(),//tamaño de la pagina
                    paginaPeliculaDb.getTotalElements(), //total de elementos deveultos por la consulta sin paginacion
                    paginaPeliculaDb.getTotalPages(),//total de paginas teniendo en cuenta el tamaño de cada pagina
                    peliculaMapper.peliculasToPeliculaList(paginaPeliculaDb.getContent()),//lista de elemento
                    paginaPeliculaDb.getSort());//ordenacio de la consulta      
    }
    
}