
package edu.alumno.helena.api_rest_bd_pelicula.srv.impl;

import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityAlreadyExistsException;
import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityNotFoundException;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.DirectorDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorList;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.helper.DirectorDependencyResolver;
import edu.alumno.helena.api_rest_bd_pelicula.repository.DirectorRepository;
import edu.alumno.helena.api_rest_bd_pelicula.srv.DirectorService;
import edu.alumno.helena.api_rest_bd_pelicula.srv.mapper.DirectorMapper;


@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;
    private final DirectorDependencyResolver dependencyResolver;

    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper,
            DirectorDependencyResolver dependencyResolver) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
        this.dependencyResolver = dependencyResolver;
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
    public void deleteDirectorById(Long id) {
        if (directorRepository.existsById(id)) {
            directorRepository.deleteById(id);
        }
        // Si no existe, simplemente no hace nada (idempotente)
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

    
  
    
}
