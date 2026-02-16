
package edu.alumno.helena.api_rest_bd_pelicula.srv.impl;

import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityAlreadyExistsException;
import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityNotFoundException;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_pelicula.exception.PeliculaNotFoundException;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PeliculaDependencyResolver;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PeliculaEntityFactory;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.DirectorDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.GeneroDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.PeliculaDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaList;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.repository.PeliculaRepository;
import edu.alumno.helena.api_rest_bd_pelicula.srv.PeliculaService;
import edu.alumno.helena.api_rest_bd_pelicula.srv.mapper.PeliculaMapper;
import io.micrometer.common.lang.NonNull;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final PeliculaMapper peliculaMapper;
    private final PeliculaEntityFactory peliculaEntityFactory;
    private final PeliculaDependencyResolver dependencyResolver;

    public PeliculaServiceImpl(PeliculaRepository peliculaRepository, PeliculaMapper peliculaMapper,
            PeliculaEntityFactory peliculaEntityFactory, PeliculaDependencyResolver dependencyResolver) {
        this.peliculaRepository = peliculaRepository;
        this.peliculaMapper = peliculaMapper;
        this.peliculaEntityFactory = peliculaEntityFactory;
        this.dependencyResolver = dependencyResolver;
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
            .orElseThrow(() -> new EntityNotFoundException("PELICULA_NOT_FOUND", "Pelicula no encontrada: " + id));
        return peliculaMapper.peliculaDbToPeliculaInfo(peliculaDb);
    }

    @Override
    public PaginaDto<PeliculaList> findAllPagePeliculaList(Pageable pagina) {
        Page<PeliculaDb> paginaPeliculaDb = peliculaRepository.findAll(pagina);
        return new PaginaDto<PeliculaList>(
                paginaPeliculaDb.getNumber(), // numero de pagina solicitada
                paginaPeliculaDb.getSize(), // tamaño de la pagina
                paginaPeliculaDb.getTotalElements(), // total de elementos deveultos por la consulta sin paginacion
                paginaPeliculaDb.getTotalPages(), // total de paginas teniendo en cuenta el tamaño de cada pagina
                peliculaMapper.peliculasToPeliculaList(paginaPeliculaDb.getContent()), // lista de elemento
                paginaPeliculaDb.getSort());// ordenacio de la consulta
    }

    @Override
    public List<PeliculaList> findPeliculasByDirector(Long directorId) {

        List<PeliculaDb> peliculasDb = peliculaRepository.findByDirectorId(directorId);
        return peliculaMapper.peliculasToPeliculaList(peliculasDb);
    }

    @Override
    public PaginaDto<PeliculaList> findByNombreContaining(String nombre, Pageable pageable) {
        Page<PeliculaDb> paginaPeliculaDb = peliculaRepository.findByTituloContainingIgnoreCase(nombre, pageable);
        return new PaginaDto<PeliculaList>(
                paginaPeliculaDb.getNumber(),
                paginaPeliculaDb.getSize(),
                paginaPeliculaDb.getTotalElements(),
                paginaPeliculaDb.getTotalPages(),
                peliculaMapper.peliculasToPeliculaList(paginaPeliculaDb.getContent()),
                paginaPeliculaDb.getSort());
    }

    @Override
    public List<PeliculaList> findPeliculasFromYear(Integer year) {
        List<PeliculaDb> peliculas = peliculaRepository.findByAño(year);
        if (peliculas.isEmpty()) {
            throw new EntityNotFoundException("PELICULA_NOT_FOUND", "No se han encontrado peliculas del año " + year);
        }
        return peliculaMapper.peliculasToPeliculaList(peliculas);
    }

    @Override
    public PeliculaInfo createPelicula(PeliculaCreate peliculaCreate) {
        // Comprobamos si ya existe una película con el mismo título (asumiendo unicidad por título)
        boolean exists = !peliculaRepository.findByTituloContainingIgnoreCase(peliculaCreate.getTitulo(), org.springframework.data.domain.Pageable.unpaged()).isEmpty();
        if (exists) {
            throw new EntityAlreadyExistsException("PELICULA_ALREADY_EXISTS", "La película ya existe con título: " + peliculaCreate.getTitulo());
        }
        DirectorDb director = dependencyResolver.requireDirector(peliculaCreate.getDirectorId());
        GeneroDb genero = dependencyResolver.requireGenero(peliculaCreate.getGeneroId());
        PeliculaDb peliculaDb = peliculaEntityFactory.fromCreate(peliculaCreate, director, genero);
        PeliculaDb saved = peliculaRepository.save(peliculaDb);
        return peliculaMapper.peliculaDbToPeliculaInfo(saved);
    }
   
    @Override
    public PeliculaInfo updatePelicula(Long id, PeliculaUpdate peliculaUpdate) {
        PeliculaDb peliculaDb = peliculaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("PELICULA_NOT_FOUND", "Pelicula no encontrada: " + id));
        DirectorDb director = dependencyResolver.optionalDirector(peliculaUpdate.getDirectorId());
        GeneroDb genero = dependencyResolver.optionalGenero(peliculaUpdate.getGeneroId());
        peliculaEntityFactory.applyUpdate(peliculaDb, peliculaUpdate, director, genero);
        PeliculaDb updated = peliculaRepository.save(peliculaDb);
        return peliculaMapper.peliculaDbToPeliculaInfo(updated);
    }
  
    @Override
    public void deletePeliculaById(Long id) {
        if (peliculaRepository.existsById(id)) {
            peliculaRepository.deleteById(id);
        }
        // Si no existe, simplemente no hace nada (idempotente)
    }

    

   

}