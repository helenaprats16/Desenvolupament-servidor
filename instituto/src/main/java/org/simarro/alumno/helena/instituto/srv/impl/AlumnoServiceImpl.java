package org.simarro.alumno.helena.instituto.srv.impl;

import org.simarro.alumno.helena.instituto.exception.EntityAlreadyExistsException;
import org.simarro.alumno.helena.instituto.exception.EntityIlegalArgumentException;
import org.simarro.alumno.helena.instituto.exception.EntityNotFoundException;
import org.simarro.alumno.helena.instituto.model.db.AlumnoEditDb;
import org.simarro.alumno.helena.instituto.model.dto.AlumnoEdit;
import org.simarro.alumno.helena.instituto.repository.AlumnoEditRepository;
import org.simarro.alumno.helena.instituto.srv.AlumnoService;
import org.simarro.alumno.helena.instituto.srv.mapper.AlumnoMapper;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImpl implements AlumnoService{
    
    private final AlumnoEditRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoEditRepository alumnoRepository){
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public AlumnoEdit create(AlumnoEdit alumnoEdit) {
        if (alumnoRepository.existsById(alumnoEdit.getDni())) {
            throw new EntityAlreadyExistsException("STUDENT_AREADY_EXIST",
                "El alumno con DNI "+alumnoEdit.getDni()+ " ya existe.");            
        }
        AlumnoEditDb entity = AlumnoMapper.INSTANCE.AlumnoEditToAlumnoEditDb(alumnoEdit);
        return AlumnoMapper.INSTANCE.AlumnoEditDbToAlumnoEdit(alumnoRepository.save(entity));
    }

    @Override
    public AlumnoEdit read(String dni) {
        AlumnoEditDb entity = alumnoRepository.findById(dni)
                .orElseThrow(() -> new EntityNotFoundException("STUDENT_NOT_FOUND",
                    "No se encontro el alumno con DNI" + dni));
        return AlumnoMapper.INSTANCE.AlumnoEditDbToAlumnoEdit(entity);       

    }

    @Override
    public AlumnoEdit update(String dni, AlumnoEdit alumnoEdit) { //Evitamos errores e inconsistencias en la logica de negocio
        if (!dni.equals(alumnoEdit.getDni())) {//Evitamos errores e inconsistencias en la lógica de negocio
            throw new EntityIlegalArgumentException("STUDENT_DNI_MISMATCH",
                "El DNI proporcionado no coincide con el DNI del alumno");
        }
        AlumnoEditDb existingEntity = alumnoRepository.findById(dni)
            .orElseThrow(() -> new EntityNotFoundException("STUDENT_NOT_FOUND_FOR_UPDATE",
                "No se puede actualizar. El alumno con DNI "+dni+" no existe."));
        
                AlumnoMapper.INSTANCE.updateAlumnoEditDbFromAlumnoEdit(alumnoEdit, existingEntity);
                return AlumnoMapper.INSTANCE.alumnoEditDbToAlumnoEdit(alumnoRepository.save(existingEntity));
    }

    @Override
    public void delete(String dni) {
       if (alumnoRepository.existsById(dni)) {
        alumnoRepository.deleteById(dni);
       }//Si no existe, no hacemos nada: el estado final "registro no existe" ya esta logrado
    }
}
