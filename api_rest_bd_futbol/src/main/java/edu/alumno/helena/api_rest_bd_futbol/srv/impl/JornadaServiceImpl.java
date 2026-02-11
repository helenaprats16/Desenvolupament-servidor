package edu.alumno.helena.api_rest_bd_futbol.srv.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_futbol.model.db.CiudadDb;
import edu.alumno.helena.api_rest_bd_futbol.model.db.JornadaDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.CiudadList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.JornadaInfo;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.JornadaList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.repository.JornadaRepository;
import edu.alumno.helena.api_rest_bd_futbol.srv.JornadaService;
import edu.alumno.helena.api_rest_bd_futbol.srv.mapper.CiudadMapper;
import edu.alumno.helena.api_rest_bd_futbol.srv.mapper.JornadaMapper;
import io.micrometer.common.lang.NonNull;

@Service
public class JornadaServiceImpl implements JornadaService {

    private final JornadaRepository jornadaRepository;

    public JornadaServiceImpl(JornadaRepository jornadaRepository){
        this.jornadaRepository = jornadaRepository;
    }

    @Override
    public Optional<JornadaInfo> getJornadaInfoByNum(Long num) {
         Optional<JornadaDb> jornadaDb = jornadaRepository.findById(num);

        if(jornadaDb.isPresent()){
            return Optional.of(JornadaMapper.INSTANCE.JornadaDbToJornadaInfo(jornadaDb.get()));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public List<JornadaList> findAllJornadaList() {
        return JornadaMapper.INSTANCE.JornadaToJornadaList(jornadaRepository.findAll());
    }

    @Override
    public List<JornadaList> findAllJornadadList(Sort sort) {
        return JornadaMapper.INSTANCE.JornadaToJornadaList(jornadaRepository.findAll(sort));
    }

    @Override
    public PaginaDto<JornadaList> findAllPageJornadaList(@NonNull Pageable pagina) {
        Page<JornadaDb> paginaJornada = jornadaRepository.findAll(pagina);
        return new PaginaDto<JornadaList>(
                    paginaJornada.getNumber(),//numero de pagina solicitada
                    paginaJornada.getSize(),//tamaño de la pagina
                    paginaJornada.getTotalElements(), //total de elementos deveultos por la consulta sin paginacion
                    paginaJornada.getTotalPages(),//total de paginas teniendo en cuenta el tamaño de cada pagina
                    JornadaMapper.INSTANCE.JornadaToJornadaList(paginaJornada.getContent()),//lista de elemento
                    paginaJornada.getSort());//ordenacio de la consulta      
    }

    @Override
    public PaginaDto<JornadaList> findByFechaContaining(String fecha, Pageable paging) {
        Page<JornadaDb> paginaJornada = jornadaRepository.findByFechaContaining(fecha, paging);
        return new PaginaDto<JornadaList>(
                    paginaJornada.getNumber(),//numero de pagina solicitada
                    paginaJornada.getSize(),//tamaño de la pagina
                    paginaJornada.getTotalElements(), //total de elementos deveultos por la consulta sin paginacion
                    paginaJornada.getTotalPages(),//total de paginas teniendo en cuenta el tamaño de cada pagina
                    JornadaMapper.INSTANCE.JornadaToJornadaList(paginaJornada.getContent()),//lista de elemento
                    paginaJornada.getSort());//ordenacio de la consulta  
    }

    




}