package edu.alumno.helena.api_rest_bd_pelicula.srv.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.DirectorDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorList;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.repository.DirectorRepository;
import edu.alumno.helena.api_rest_bd_pelicula.srv.DirectorService;
import edu.alumno.helena.api_rest_bd_pelicula.srv.mapper.DirectorMapper;


@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
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
        return directorMapper.directorDbToDirectorInfo(directorRepository.findById(id).orElseThrow(() -> new RuntimeException("Director no encontrado"))
        );
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
    public void deleteDirectorById(Long id) {
        if (!directorRepository.existsById(id)) {
            throw new RuntimeException("Director no encontrado: " + id);
        }
        directorRepository.deleteById(id);
    }

    @Override
    public DirectorInfo createDirector(DirectorDb directorDb) {
        DirectorDb savedDirector = directorRepository.save(directorDb);
        return DirectorMapper.INSTANCE.directorDbToDirectorInfo(savedDirector);
    }

    @Override
    public DirectorInfo updateDirector(Long id, DirectorDb directorDetails) {
        DirectorDb director = directorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Director no encontrado: " + id));

        // Actualizar campos
        if (directorDetails.getNombre() != null) {
            director.setNombre(directorDetails.getNombre());
        }
        if (directorDetails.getNacionalidad() != null) {
            director.setNacionalidad(directorDetails.getNacionalidad());
        }

        DirectorDb updatedDirector = directorRepository.save(director);
        return DirectorMapper.INSTANCE.directorDbToDirectorInfo(updatedDirector);
    }

    
  
    
}
