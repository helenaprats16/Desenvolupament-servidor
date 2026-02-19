
package edu.alumno.helena.api_rest_bd_pelicula.srv.impl;

import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityAlreadyExistsException;
import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.GeneroDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.helper.FiltroException;
import edu.alumno.helena.api_rest_bd_pelicula.helper.GeneroDependencyResolver;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginaResponse;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationFactory;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PeticionListadoFiltrado;
import edu.alumno.helena.api_rest_bd_pelicula.repository.GeneroRepository;
import edu.alumno.helena.api_rest_bd_pelicula.repository.PeliculaRepository;
import edu.alumno.helena.api_rest_bd_pelicula.srv.GeneroService;
import edu.alumno.helena.api_rest_bd_pelicula.srv.mapper.GeneroMapper;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PeticionListadoFiltradoConverter;
import edu.alumno.helena.api_rest_bd_pelicula.srv.specification.FiltroBusquedaSpecification;

@Service
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;
    private final PeliculaRepository peliculaRepository;
    private final JdbcTemplate jdbcTemplate;
    private final GeneroDependencyResolver dependencyResolver;
    private final PaginationFactory paginationFactory;
    private final PeticionListadoFiltradoConverter peticionConverter;

    public GeneroServiceImpl(GeneroRepository generoRepository, GeneroMapper generoMapper,
            PeliculaRepository peliculaRepository,
            JdbcTemplate jdbcTemplate,
            GeneroDependencyResolver dependencyResolver, PaginationFactory paginationFactory,
            PeticionListadoFiltradoConverter peticionConverter) {
        this.generoRepository = generoRepository;
        this.generoMapper = generoMapper;
        this.peliculaRepository = peliculaRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.dependencyResolver = dependencyResolver;
        this.paginationFactory = paginationFactory;
        this.peticionConverter = peticionConverter;
    }

    @Override
    public PaginaDto<GeneroInfo> findAllPageGeneros(Pageable pagina) {
        Page<GeneroDb> paginaGeneroDb = generoRepository.findAll(pagina);
        return new PaginaDto<GeneroInfo>(
                paginaGeneroDb.getNumber(),
                paginaGeneroDb.getSize(),
                paginaGeneroDb.getTotalElements(),
                paginaGeneroDb.getTotalPages(),
                generoMapper.generosToGeneroInfo(paginaGeneroDb.getContent()),
                paginaGeneroDb.getSort());
    }

    @Override
    public PaginaDto<GeneroInfo> findByNombreContaining(String nombre, Pageable pagina) {
        Page<GeneroDb> paginaGeneroDb = generoRepository.findByNombreContainingIgnoreCase(nombre, pagina);
        return new PaginaDto<GeneroInfo>(
                paginaGeneroDb.getNumber(),
                paginaGeneroDb.getSize(),
                paginaGeneroDb.getTotalElements(),
                paginaGeneroDb.getTotalPages(),
                generoMapper.generosToGeneroInfo(paginaGeneroDb.getContent()),
                paginaGeneroDb.getSort());
    }

    @Override
    public PaginaResponse<GeneroInfo> findAll(String[] filter, int page, int size, String[] sort)
            throws FiltroException {
        PeticionListadoFiltrado peticion = peticionConverter.convertFromParams(filter, page, size, sort);
        return findAll(peticion);
    }

    @Override
    public PaginaResponse<GeneroInfo> findAll(PeticionListadoFiltrado peticionListadoFiltrado)
            throws FiltroException {
        try {
            Pageable pageable = paginationFactory.createPageable(peticionListadoFiltrado);
            Specification<GeneroDb> filtrosBusquedaSpecification =
                new FiltroBusquedaSpecification<GeneroDb>(peticionListadoFiltrado.getListaFiltros());
            Page<GeneroDb> page = generoRepository.findAll(filtrosBusquedaSpecification, pageable);
            return new PaginaResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                generoMapper.generosToGeneroInfo(page.getContent()),
                peticionListadoFiltrado.getListaFiltros(),
                peticionListadoFiltrado.getSort()
            );
        } catch (JpaSystemException e) {
            String cause = "";
            if (e.getRootCause() != null && e.getCause() != null && e.getCause().getMessage() != null) {
                cause = e.getRootCause().getMessage();
            }
            throw new FiltroException("BAD_OPERATOR_FILTER",
                "Error: No se puede realizar esa operacion sobre el atributo por el tipo de dato",
                e.getMessage() + ":" + cause);
        } catch (PropertyReferenceException e) {
            throw new FiltroException("BAD_ATTRIBUTE_ORDER",
                "Error: No existe el nombre del atributo de ordenacion en la tabla", e.getMessage());
        } catch (InvalidDataAccessApiUsageException e) {
            throw new FiltroException("BAD_ATTRIBUTE_FILTER",
                "Error: Posiblemente no existe el atributo en la tabla", e.getMessage());
        }
    }

    @Override
    public GeneroInfo getGeneroInfoById(Long id) {
        GeneroDb genero = generoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("GENERO_NOT_FOUND", "Genero no encontrado: " + id));
        return generoMapper.generoDbToGeneroInfo(genero);
    }

    @Override
    public GeneroInfo createGenero(GeneroCreate generoCreate) {
        // Comprovem si existeix un gènere amb el mateix nom (assumint unicitat per nom)
        boolean exists = !generoRepository.findByNombreContainingIgnoreCase(generoCreate.getNombre(), org.springframework.data.domain.Pageable.unpaged()).isEmpty();
        if (exists) {
            throw new EntityAlreadyExistsException("GENERO_ALREADY_EXISTS", "El gènere ja existeix amb nom: " + generoCreate.getNombre());
        }
        GeneroDb generoDb = generoMapper.generoCreateToGeneroDb(generoCreate);
        GeneroDb saved = generoRepository.save(generoDb);
        return generoMapper.generoDbToGeneroInfo(saved);
    }

    @Override
    public GeneroInfo updateGenero(Long id, GeneroUpdate generoUpdate) {
        GeneroDb generoDb = generoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("GENERO_NOT_FOUND", "Gènere no trobat: " + id));
        if (generoUpdate.getNombre() != null) {
            generoDb.setNombre(generoUpdate.getNombre());
        }
        if (generoUpdate.getDescripcion() != null) {
            generoDb.setDescripcion(generoUpdate.getDescripcion());
        }
        GeneroDb updated = generoRepository.save(generoDb);
        return generoMapper.generoDbToGeneroInfo(updated);
    }

    @Override
    @Transactional
    public void deleteGeneroById(Long id) {
        if (!generoRepository.existsById(id)) {
            return;
        }
        jdbcTemplate.update("DELETE FROM valoracion WHERE pelicula_id IN (SELECT id FROM pelicula WHERE genero_id = ?)", id);
        peliculaRepository.deleteByGeneroId(id);
        generoRepository.deleteById(id);
    }

  

}
