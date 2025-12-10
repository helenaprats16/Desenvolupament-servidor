package edu.alumno.helena.api_rest_bd_futbol.srv;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.alumno.helena.api_rest_bd_futbol.model.dto.CiudadInfo;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.CiudadList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import io.micrometer.common.lang.NonNull;

public interface CiudadService {

    public CiudadInfo getCiudadInfoById(@NonNull Long id);
    public List<CiudadList> findAllCiudadList();
    public List<CiudadList> findAllCiudadList(@NonNull Sort sort);
    public Optional<Object> getCiudadesByNombreContainingListOrderByName(String palabra, Sort sort);
    public PaginaDto<CiudadList> findAllPageCiudadList(@NonNull Pageable pagina);
    public PaginaDto<CiudadList> findByNombreContaining(String nombre, Pageable paging);
}