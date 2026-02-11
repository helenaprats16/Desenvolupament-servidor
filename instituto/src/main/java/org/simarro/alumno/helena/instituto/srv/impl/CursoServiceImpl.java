package org.simarro.alumno.helena.instituto.srv.impl;

import org.simarro.alumno.helena.instituto.exception.EntityIlegalArgumentException;
import org.simarro.alumno.helena.instituto.exception.EntityNotFoundException;
import org.simarro.alumno.helena.instituto.model.db.CursoEditDb;
import org.simarro.alumno.helena.instituto.model.dto.CursoEdit;
import org.simarro.alumno.helena.instituto.repository.CursoEditRepository;
import org.simarro.alumno.helena.instituto.srv.CursoService;
import org.simarro.alumno.helena.instituto.srv.mapper.CursoMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Esta notacion Lombok permite no tener que declarar el constructor
public class CursoServiceImpl implements CursoService {

    private final CursoEditRepository cursoRepository;

    @Override
    public CursoEdit create(CursoEdit curso) {
        if (curso.getId() != null) { // Como el id lo crea la BD no debemos pasarle un valor inicial
        throw new EntityIlegalArgumentException("COURSE_ID_MISMATCH",
        "El ID debe ser nulo al crear un nuevo curso.");
    }

        CursoEditDb entity = CursoMapper.INSTANCE.CursoEditToCursoEditDb(curso);
        return CursoMapper.INSTANCE.CursoEditDbToCursoEdit(cursoRepository.save(entity));
    }

    @Override
    public CursoEdit read(Long id) {
        CursoEditDb entity = cursoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("COURSE_NOT_FOUND",
                        "El curso con ID "+ id + " no existe"));
        return CursoMapper.INSTANCE.CursoEditDbToCursoEdit(entity);
    }

    @Override
    public CursoEdit update(Long id, CursoEdit curso) {
        if (!id.equals(curso.getId())) {
            throw new EntityIlegalArgumentException("COURSE_ID_MISMATCH",
                "El ID proporcionado no coincide con el ID del curso");   
        }

        CursoEditDb existingEntity = cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("COURSE_NOT_FOUND_FOR_UPDATE",
                        "No se puede actualizar. El curso con ID "+ id+" no existe."));
            CursoMapper.INSTANCE.updateCursoEditDbFromCursoEdit(curso, existingEntity);           
            return CursoMapper.INSTANCE.CursoEditDbToCursoEdit(cursoRepository.save(existingEntity));
        }   

    @Override
    public void delete(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } // Si no existe, no hacemos nada: el estado final "registro no existe" ya está
    
    }
}
