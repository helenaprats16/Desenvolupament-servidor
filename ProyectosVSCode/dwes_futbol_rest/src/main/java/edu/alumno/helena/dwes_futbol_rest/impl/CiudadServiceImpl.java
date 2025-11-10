package edu.alumno.helena.dwes_futbol_rest.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.alumno.helena.dwes_futbol_rest.model.db.CiudadDb;
import edu.alumno.helena.dwes_futbol_rest.model.dto.CiudadInfo;
import edu.alumno.helena.dwes_futbol_rest.model.dto.CiudadList;
import edu.alumno.helena.dwes_futbol_rest.model.dto.PaginaDto;
import edu.alumno.helena.dwes_futbol_rest.repository.CiudadRepository;
import edu.alumno.helena.dwes_futbol_rest.srv.CiudadService;
import edu.alumno.helena.dwes_futbol_rest.srv.mapper.CiudadMapper;
import io.micrometer.common.lang.NonNull;

@Service
public class CiudadServiceImpl implements CiudadService{

    private final CiudadRepository ciudadRepository;

    public CiudadServiceImpl(CiudadRepository ciudadRepository){
        this.ciudadRepository = ciudadRepository;
    }

    public List<CiudadList> findAllCiudadList() {
        return CiudadMapper.INSTANCE.ciudadesToCiudadList(ciudadRepository.findAll());
    }
    
    public List<CiudadList> findAllCiudadList(@NonNull Sort sort){
        return CiudadMapper.INSTANCE.ciudadesToCiudadList(ciudadRepository.findAll(sort));
    }


    public PaginaDto<CiudadList> findAllPageCiudadList(@NonNull Pageable pagina){
        Page<CiudadDb> paginaCiudadDb = ciudadRepository.findAll(pagina);
        return new PaginaDto<CiudadList>(
                    paginaCiudadDb.getNumber(),//numero de pagina solicitada
                    paginaCiudadDb.getSize(),//tamaño de la pagina
                    paginaCiudadDb.getTotalElements(), //total de elementos deveultos por la consulta sin paginacion
                    paginaCiudadDb.getTotalPages(),//total de paginas teniendo en cuenta el tamaño de cada pagina
                    CiudadMapper.INSTANCE.ciudadesToCiudadList(paginaCiudadDb.getContent()),//lista de elemento
                    paginaCiudadDb.getSort());//ordenacio de la consulta      
    }

    @Override
    public PaginaDto<CiudadList> findByNombreContaining(String nombre, Pageable pagina) {
        Page<CiudadDb> paginaCiudadDb = ciudadRepository.findByNombreContaining(nombre, pagina);
        return new PaginaDto<CiudadList>(
                paginaCiudadDb.getNumber(),
                paginaCiudadDb.getSize(),
                paginaCiudadDb.getTotalElements(),
                paginaCiudadDb.getTotalPages(),
                CiudadMapper.INSTANCE.ciudadesToCiudadList(paginaCiudadDb.getContent()),
                paginaCiudadDb.getSort());
    }



    @Override
    public Optional<Object> getCiudadesByNombreContainingListOrderByName(String palabra, Sort sort) {
        List<CiudadDb> ciudades = ciudadRepository.findByNombreContaining(palabra, sort);
        if (ciudades.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(CiudadMapper.INSTANCE.ciudadesToCiudadList(ciudades));
    }

  



    @Override
    public Optional<CiudadInfo> getCiudadInfoById(@NonNull Long id) {
        Optional<CiudadDb> ciudadDb = ciudadRepository.findById(id);

        if(ciudadDb.isPresent()){
            return Optional.of(CiudadMapper.INSTANCE.CiudadDbToCiudadInfo(ciudadDb.get()));
        }else{
            return Optional.empty();
        }
    }

    




}