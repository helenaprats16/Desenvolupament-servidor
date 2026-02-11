package org.simarro.alumno.helena.instituto.srv.impl;

import org.simarro.alumno.helena.instituto.exception.EntityIlegalArgumentException;
import org.simarro.alumno.helena.instituto.exception.EntityNotFoundException;
import org.simarro.alumno.helena.instituto.model.db.MatriculaEditDb;
import org.simarro.alumno.helena.instituto.model.dto.MatriculaEdit;
import org.simarro.alumno.helena.instituto.repository.MatriculaEditRepository;
import org.simarro.alumno.helena.instituto.srv.MatriculaService;
import org.simarro.alumno.helena.instituto.srv.mapper.MatriculaMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Esta notacion Lombok permite no tener que declarar el constructor
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaEditRepository matriculaRepository;

    @Override
    public MatriculaEdit create(MatriculaEdit matricula) {
        if (matricula.getId() != null) { // Como el id lo crea la BD no debemos pasarle un valor inicial
        throw new EntityIlegalArgumentException("ENROLLMENT_ID_MISMATCH",
        "El ID debe ser nulo al crear una nueva matrícula.");
    }

        MatriculaEditDb entity = MatriculaMapper.INSTANCE.MatriculaEditToMatriculaEditDb(matricula);
        return MatriculaMapper.INSTANCE.MatriculaEditDbToMatriculaEdit(matriculaRepository.save(entity));
    }

    @Override
    public MatriculaEdit read(Long id) {
        MatriculaEditDb entity = matriculaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("ENROLLMENT_NOT_FOUND",
                        "La matrícula con ID "+ id + " no existe"));
        return MatriculaMapper.INSTANCE.MatriculaEditDbToMatriculaEdit(entity);
    }

    @Override
    public MatriculaEdit update(Long id, MatriculaEdit matricula) {
        if (!id.equals(matricula.getId())) {
            throw new EntityIlegalArgumentException("ENROLLMENT_ID_MISMATCH",
                "El ID proporcionado no coincide con el ID de la matrícula");   
        }

        MatriculaEditDb existingEntity = matriculaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ENROLLMENT_NOT_FOUND_FOR_UPDATE",
                        "No se puede actualizar. La matrícula con ID "+ id+" no existe."));
            MatriculaMapper.INSTANCE.updateMatriculaEditDbFromMatriculaEdit(matricula, existingEntity);           
            return MatriculaMapper.INSTANCE.MatriculaEditDbToMatriculaEdit(matriculaRepository.save(existingEntity));
        }   

    @Override
    public void delete(Long id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
        } // Si no existe, no hacemos nada: el estado final "registro no existe" ya está
    
    }
}
