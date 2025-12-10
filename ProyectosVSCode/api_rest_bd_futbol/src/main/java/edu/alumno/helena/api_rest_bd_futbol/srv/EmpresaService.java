package edu.alumno.helena.api_rest_bd_futbol.srv;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.alumno.helena.api_rest_bd_futbol.model.dto.EmpresaInfo;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EmpresaList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import io.micrometer.common.lang.NonNull;

public interface EmpresaService {

    public List<EmpresaList> findAllEmpresaList();
    public List<EmpresaList> findAllEmpresaList(@NonNull Sort sort);
    public EmpresaInfo getEmpresaInfoById(@NonNull Long id);
    public PaginaDto<EmpresaList> findAllPageEmpresaList(@NonNull Pageable pagina);
    void deleteEmpresaById(Long id);

}