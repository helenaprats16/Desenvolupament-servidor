package edu.alumno.helena.api_rest_bd_futbol.srv.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_futbol.model.db.CiudadDb;
import edu.alumno.helena.api_rest_bd_futbol.model.db.EmpresaDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.CiudadList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EmpresaInfo;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EmpresaList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_futbol.repository.EmpresaRepository;
import edu.alumno.helena.api_rest_bd_futbol.srv.EmpresaService;
import edu.alumno.helena.api_rest_bd_futbol.srv.mapper.CiudadMapper;
import edu.alumno.helena.api_rest_bd_futbol.srv.mapper.EmpresaMapper;
import io.micrometer.common.lang.NonNull;

@Service
public class EmpresaServiceImpl implements EmpresaService{

    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository){
        this.empresaRepository = empresaRepository;
    }


    @Override
    public List<EmpresaList> findAllEmpresaList() {
        return EmpresaMapper.INSTANCE.empresaToEmpresaList(empresaRepository.findAll());
    }

    @Override
    public List<EmpresaList> findAllEmpresaList(Sort sort) {
        return EmpresaMapper.INSTANCE.empresaToEmpresaList(empresaRepository.findAll(sort));
    }


    @Override
    public PaginaDto<EmpresaList> findAllPageEmpresaList(Pageable pagina) {
       Page<EmpresaDb> paginaEmpresaDb = empresaRepository.findAll(pagina);
        return new PaginaDto<EmpresaList>(
                    paginaEmpresaDb.getNumber(),//numero de pagina solicitada
                    paginaEmpresaDb.getSize(),//tamaño de la pagina
                    paginaEmpresaDb.getTotalElements(), //total de elementos deveultos por la consulta sin paginacion
                    paginaEmpresaDb.getTotalPages(),//total de paginas teniendo en cuenta el tamaño de cada pagina
                    EmpresaMapper.INSTANCE.empresaToEmpresaList(paginaEmpresaDb.getContent()),//lista de elemento
                    paginaEmpresaDb.getSort());//ordenacio de la consulta   
    }


    @Override
    public EmpresaInfo getEmpresaInfoById(Long id) {
        EmpresaDb empresaDb = empresaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empresa no encontrada: " + id));
        // usar un método de mapper que convierta la entidad a EmpresaInfo
        return EmpresaMapper.INSTANCE.empresaToEmpresaInfo(empresaDb);
    }


    @Override
    public void deleteEmpresaById(Long id) {
         if (!empresaRepository.existsById(id)) {
            throw new RuntimeException("Empresa no encontrada: " + id);
        }
        empresaRepository.deleteById(id);
    }


   

   


}