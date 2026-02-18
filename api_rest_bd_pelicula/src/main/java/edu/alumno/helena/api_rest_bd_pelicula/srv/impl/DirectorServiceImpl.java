
package edu.alumno.helena.api_rest_bd_pelicula.srv.impl;

import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityAlreadyExistsException;
import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityNotFoundException;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.DirectorDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorEstadistica;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorList;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.helper.DirectorDependencyResolver;
import edu.alumno.helena.api_rest_bd_pelicula.helper.FiltroException;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginaResponse;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginationFactory;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PeticionListadoFiltrado;
import edu.alumno.helena.api_rest_bd_pelicula.repository.DirectorRepository;
import edu.alumno.helena.api_rest_bd_pelicula.srv.DirectorService;
import edu.alumno.helena.api_rest_bd_pelicula.srv.mapper.DirectorMapper;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PeticionListadoFiltradoConverter;
import edu.alumno.helena.api_rest_bd_pelicula.srv.specification.FiltroBusquedaSpecification;


@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;
    private final DirectorDependencyResolver dependencyResolver;
    private final PaginationFactory paginationFactory;
    private final PeticionListadoFiltradoConverter peticionConverter;

    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper,
            DirectorDependencyResolver dependencyResolver, PaginationFactory paginationFactory,
            PeticionListadoFiltradoConverter peticionConverter) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
        this.dependencyResolver = dependencyResolver;
        this.paginationFactory = paginationFactory;
        this.peticionConverter = peticionConverter;
    }

    @Override
    public List<DirectorList> findAllDirectorList() {
    
        return directorMapper.directorsToDirectorList(directorRepository.findAll());
    }

   @Override
    public List<DirectorList> findAllDirectorList(Sort sort) {

        return directorMapper.directorsToDirectorList(directorRepository.findAll(sort));
    }


    @Override
    public DirectorInfo getDirectorInfoById(Long id) {
        DirectorDb director = directorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("DIRECTOR_NOT_FOUND", "Director no encontrado: " + id));
        return directorMapper.directorDbToDirectorInfo(director);
    }

    @Override
    public PaginaDto<DirectorList> findAllPageDirectorList(Pageable pagina) {
         Page<DirectorDb> paginaDirectorDb = directorRepository.findAll(pagina);
        return new PaginaDto<DirectorList>(
                    paginaDirectorDb.getNumber(),//numero de pagina solicitada
                    paginaDirectorDb.getSize(),//tamaño de la pagina
                    paginaDirectorDb.getTotalElements(), //total de elementos deveultos por la consulta sin paginacion
                    paginaDirectorDb.getTotalPages(),//total de paginas teniendo en cuenta el tamaño de cada pagina
                    directorMapper.directorsToDirectorList(paginaDirectorDb.getContent()),//lista de elemento
                    paginaDirectorDb.getSort());//ordenacio de la consulta      
    }

    @Override
    public PaginaDto<DirectorList> findByNombreContaining(String nombre, Pageable pagina) {
        Page<DirectorDb> paginaDirectorDb = directorRepository.findByNombreContainingIgnoreCase(nombre, pagina);
        return new PaginaDto<DirectorList>(
                paginaDirectorDb.getNumber(),
                paginaDirectorDb.getSize(),
                paginaDirectorDb.getTotalElements(),
                paginaDirectorDb.getTotalPages(),
                directorMapper.directorsToDirectorList(paginaDirectorDb.getContent()),
                paginaDirectorDb.getSort());
    }

    @Override
    public PaginaResponse<DirectorList> findAll(String[] filter, int page, int size, String[] sort)
            throws FiltroException {
        PeticionListadoFiltrado peticion = peticionConverter.convertFromParams(filter, page, size, sort);
        return findAll(peticion);
    }

    @Override
    public PaginaResponse<DirectorList> findAll(PeticionListadoFiltrado peticionListadoFiltrado)
            throws FiltroException {
        try {
            Pageable pageable = paginationFactory.createPageable(peticionListadoFiltrado);
            Specification<DirectorDb> filtrosBusquedaSpecification =
                new FiltroBusquedaSpecification<DirectorDb>(peticionListadoFiltrado.getListaFiltros());
            Page<DirectorDb> page = directorRepository.findAll(filtrosBusquedaSpecification, pageable);
            return new PaginaResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                directorMapper.directorsToDirectorList(page.getContent()),
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
    public void deleteDirectorById(Long id) {
        if (!directorRepository.existsById(id)) {
            throw new EntityNotFoundException("DIRECTOR_NOT_FOUND", "Director no encontrado: " + id);
        }
        directorRepository.deleteById(id);
    }

    @Override
    public DirectorInfo createDirector(DirectorCreate directorCreate) {
        // Comprobamos si ya existe un director con el mismo nombre (asumiendo unicidad por nombre)
        boolean exists = !directorRepository.findByNombreContainingIgnoreCase(directorCreate.getNombre(), org.springframework.data.domain.Pageable.unpaged()).isEmpty();
        if (exists) {
            throw new EntityAlreadyExistsException("DIRECTOR_ALREADY_EXISTS", "El director ya existe con nombre: " + directorCreate.getNombre());
        }
        DirectorDb directorDb = directorMapper.directorCreateToDirectorDb(directorCreate);
        DirectorDb savedDirector = directorRepository.save(directorDb);
        return directorMapper.directorDbToDirectorInfo(savedDirector);
    }

    @Override
    public DirectorInfo updateDirector(Long id, DirectorUpdate directorUpdate) {
        DirectorDb director = directorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("DIRECTOR_NOT_FOUND", "Director no encontrado: " + id));

        // Actualizar campos
        if (directorUpdate.getNombre() != null) {
            director.setNombre(directorUpdate.getNombre());
        }
        if (directorUpdate.getNacionalidad() != null) {
            director.setNacionalidad(directorUpdate.getNacionalidad());
        }

        DirectorDb updatedDirector = directorRepository.save(director);
        return directorMapper.directorDbToDirectorInfo(updatedDirector);
    }

    @Override
    public List<DirectorEstadistica> getEstadisticasDirectores() {
        return directorRepository.findDirectoresConEstadisticas();
    }

    @Override
    public List<DirectorEstadistica> getDirectoresConMinimoPeliculas(Long minPeliculas) {
        if (minPeliculas == null || minPeliculas < 0) {
            minPeliculas = 1L;
        }
        return directorRepository.findDirectoresConMinimoPeliculas(minPeliculas);
    }
  
    
}
