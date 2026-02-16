
package edu.alumno.helena.api_rest_bd_pelicula.srv.impl;

import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityAlreadyExistsException;
import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.GeneroDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.helper.GeneroDependencyResolver;
import edu.alumno.helena.api_rest_bd_pelicula.repository.GeneroRepository;
import edu.alumno.helena.api_rest_bd_pelicula.srv.GeneroService;
import edu.alumno.helena.api_rest_bd_pelicula.srv.mapper.GeneroMapper;

@Service
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;
    private final GeneroDependencyResolver dependencyResolver;

    public GeneroServiceImpl(GeneroRepository generoRepository, GeneroMapper generoMapper,
            GeneroDependencyResolver dependencyResolver) {
        this.generoRepository = generoRepository;
        this.generoMapper = generoMapper;
        this.dependencyResolver = dependencyResolver;
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
    public GeneroInfo getGeneroInfoById(Long id) {
        GeneroDb genero = generoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("GENERO_NOT_FOUND", "Genero no encontrado: " + id));
        return generoMapper.generoDbToGeneroInfo(genero);
    }

    @Override
    public GeneroInfo createGenero(GeneroCreate generoCreate) {
        // Comprobamos si ya existe un género con el mismo nombre (asumiendo unicidad por nombre)
        boolean exists = !generoRepository.findByNombreContainingIgnoreCase(generoCreate.getNombre(), org.springframework.data.domain.Pageable.unpaged()).isEmpty();
        if (exists) {
            throw new EntityAlreadyExistsException("GENERO_ALREADY_EXISTS", "El género ya existe con nombre: " + generoCreate.getNombre());
        }
        GeneroDb generoDb = generoMapper.generoCreateToGeneroDb(generoCreate);
        GeneroDb saved = generoRepository.save(generoDb);
        return generoMapper.generoDbToGeneroInfo(saved);
    }

    @Override
    public GeneroInfo updateGenero(Long id, GeneroUpdate generoUpdate) {
        GeneroDb generoDb = generoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("GENERO_NOT_FOUND", "Genero no encontrado: " + id));
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
    public void deleteGeneroById(Long id) {
        if (generoRepository.existsById(id)) {
            generoRepository.deleteById(id);
        }
        // Si no existe, simplemente no hace nada (idempotente)
    }

  

}
