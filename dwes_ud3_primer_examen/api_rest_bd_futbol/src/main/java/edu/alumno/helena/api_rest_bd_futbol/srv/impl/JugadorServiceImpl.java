package edu.alumno.helena.api_rest_bd_futbol.srv.impl;


import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_futbol.exception.FiltroException;
import edu.alumno.helena.api_rest_bd_futbol.helper.PaginationFactory;
import edu.alumno.helena.api_rest_bd_futbol.helper.PeticionListadoFiltradoConverter;
import edu.alumno.helena.api_rest_bd_futbol.model.db.JugadorDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.JugadorList;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PaginaResponse;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.PeticionListadoFiltrado;
import edu.alumno.helena.api_rest_bd_futbol.repository.JugadorRepository;
import edu.alumno.helena.api_rest_bd_futbol.srv.JugadorService;
import edu.alumno.helena.api_rest_bd_futbol.srv.mapper.JugadorMapper;
import edu.alumno.helena.api_rest_bd_futbol.srv.specification.FiltroBusquedaSpecification;

@Service
public class JugadorServiceImpl implements JugadorService {
    private final JugadorRepository jugadorRepository;
    private final PaginationFactory paginationFactory;
    private final PeticionListadoFiltradoConverter peticionConverter;
    
    public JugadorServiceImpl(
            JugadorRepository jugadorRepository, 
            PaginationFactory paginationFactory,
            PeticionListadoFiltradoConverter peticionConverter) {
        this.jugadorRepository = jugadorRepository;
        this.paginationFactory = paginationFactory;
        this.peticionConverter = peticionConverter;
    }

    @Override
    public PaginaResponse<JugadorList> findAll(String[] filter, int page, int size, String[] sort) 
            throws FiltroException {
        /** 'peticionConverter' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        PeticionListadoFiltrado peticion = peticionConverter.convertFromParams(filter, page, size, sort);
        return findAll(peticion);// En vez de hacer 2 veces el filtrado llamamos al método que lo centraliza
    }

   
    @SuppressWarnings("null")
    @Override
    public PaginaResponse<JugadorList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException {
        /** 'paginationFactory' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        try {
            // Configurar ordenamiento
            Pageable pageable = paginationFactory.createPageable(peticionListadoFiltrado);
            // Configurar criterio de filtrado con Specification
            Specification<JugadorDb> filtrosBusquedaSpecification = new FiltroBusquedaSpecification<JugadorDb>(
                peticionListadoFiltrado.getListaFiltros());
            // Filtrar y ordenar: puede producir cualquier de los errores controlados en el catch
            Page<JugadorDb> page = jugadorRepository.findAll(filtrosBusquedaSpecification, pageable);
            //Devolver respuesta
            return JugadorMapper.pageToPaginaResponse(
                page,
                peticionListadoFiltrado.getListaFiltros(), 
                peticionListadoFiltrado.getSort());
        } catch (JpaSystemException e) {
            String cause="";
            if  (e.getRootCause()!=null){
                if (e.getCause().getMessage()!=null)
                    cause= e.getRootCause().getMessage();
            }
            throw new FiltroException("BAD_OPERATOR_FILTER",
                    "Error: No se puede realizar esa operación sobre el atributo por el tipo de dato", e.getMessage()+":"+cause);
        } catch (PropertyReferenceException e) {
            throw new FiltroException("BAD_ATTRIBUTE_ORDER",
                    "Error: No existe el nombre del atributo de ordenación en la tabla", e.getMessage());
        } catch (InvalidDataAccessApiUsageException e) {
            throw new FiltroException("BAD_ATTRIBUTE_FILTER", "Error: Posiblemente no existe el atributo en la tabla",
                    e.getMessage());
        }
    }
}
