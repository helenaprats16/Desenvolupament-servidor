package org.simarro.alumno.helena.instituto.srv.impl;

import org.simarro.alumno.helena.instituto.exception.EntityIlegalArgumentException;
import org.simarro.alumno.helena.instituto.exception.EntityNotFoundException;
import org.simarro.alumno.helena.instituto.model.db.ProfesorEditDb;
import org.simarro.alumno.helena.instituto.model.dto.ProfesorEdit;
import org.simarro.alumno.helena.instituto.repository.ProfesorEditRepository;
import org.simarro.alumno.helena.instituto.srv.ProfesorService;
import org.simarro.alumno.helena.instituto.srv.mapper.ProfesorMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Esta notacion Lombok permite no tener que declarar el constructor
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorEditRepository profesorRepository;

    @Override
    public ProfesorEdit create(ProfesorEdit profesor) {
        if (profesor.getId() != null) { // Como el id lo crea la BD no debemos pasarle un valor inicial
        throw new EntityIlegalArgumentException("TEACHER_ID_MISMATCH",
        "El ID debe ser nulo al crear un nuevo profesor.");
    }

        ProfesorEditDb entity = ProfesorMapper.INSTANCE.ProfesorEditToProfesorEditDb(profesor);
        return ProfesorMapper.INSTANCE.ProfesorEditDbToProfesorEdit(profesorRepository.save(entity));
    }

    @Override
    public ProfesorEdit read(Long id) {
        ProfesorEditDb entity = profesorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("TEACHER_NOT_FOUND",
                        "El profesor con ID "+ id + " no existe"));
        return ProfesorMapper.INSTANCE.ProfesorEditDbToProfesorEdit(entity);
    }

    @Override
    public ProfesorEdit update(Long id, ProfesorEdit profesor) {
        if (!id.equals(profesor.getId())) {
            throw new EntityIlegalArgumentException("TEACHER_ID_MISMATCH",
                "El ID proporcionado no coincide con el ID del profesor");   
        }

        ProfesorEditDb existingEntity = profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TEACHER_NOT_FOUND_FOR_UPDATE",
                        "No se puede actulizar. El profesor con ID "+ id+" no existe."));
            ProfesorMapper.INSTANCE.updateProfesorEditDbFromProfesorEdit(profesor, existingEntity);           
            return ProfesorMapper.INSTANCE.ProfesorEditDbToProfesorEdit(profesorRepository.save(existingEntity));
        }   

    @Override
    public void delete(Long id) {
        if (profesorRepository.existsById(id)) {
            profesorRepository.deleteById(id);
        } // Si no existe, no hacemos nada: el estado final "registro no existe" ya está
    
    }
}
