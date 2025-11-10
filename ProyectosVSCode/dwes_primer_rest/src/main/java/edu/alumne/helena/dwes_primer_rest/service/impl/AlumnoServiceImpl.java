package edu.alumne.helena.dwes_primer_rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.alumne.helena.dwes_primer_rest.model.db.AlumnoDb;
import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoEdit;
import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoInfo;
import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoList;
import edu.alumne.helena.dwes_primer_rest.repository.AlumnoRepository;
import edu.alumne.helena.dwes_primer_rest.service.AlumnoService;
import edu.alumne.helena.dwes_primer_rest.service.mapper.AlumnoMapper;


@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Optional<AlumnoEdit> getAlumnoEditByDni(String dni) {
        Optional<AlumnoDb> alumnoDb=alumnoRepository.findById(dni);
        if (alumnoDb.isPresent())
            return Optional.of(AlumnoMapper.INSTANCE.alumnoDbToAlumnoEdit(alumnoDb.get()));
        else 
            return Optional.empty();
    }

    @Override
    public Optional<AlumnoInfo> getAlumnoInfoByDni(String dni) {
        Optional<AlumnoDb> alumnoDb=alumnoRepository.findById(dni);
        if (alumnoDb.isPresent())
            return Optional.of(AlumnoMapper.INSTANCE.alumnoDbToAlumnoInfo((alumnoDb.get())));
        else 
            return Optional.empty();
    }

    @Override
    public AlumnoEdit save(AlumnoEdit alumnoEdit) {
        AlumnoDb alumnoDb = AlumnoMapper.INSTANCE.alumnoEditToAlumnoDb(alumnoEdit);
        alumnoRepository.save(alumnoDb);
        return AlumnoMapper.INSTANCE.alumnoDbToAlumnoEdit(alumnoDb);
    }

    @Override
    public String deleteByDni(String dni) {
        return alumnoRepository.findById(dni)
        .map(a-> {
                alumnoRepository.deleteById(dni);
                return "Deleted";
            }).orElse("Not Deleted");
    }

    @Override
    public Optional<AlumnoEdit> update(AlumnoEdit alumnoEdit) {
         Optional<AlumnoDb> alumnoDb=alumnoRepository.findById(alumnoEdit.getDni());
        if (alumnoDb.isPresent())
            return alumnoDb.map(a -> save(alumnoEdit));
        else 
            return Optional.empty();
    }

    @Override
    public List<AlumnoList> findAllAlumnoList() {
        List<AlumnoDb> llistaAlumne = alumnoRepository.findAll();
        List<AlumnoList> llistaAlumneList = AlumnoMapper.INSTANCE.alumnosToAlumnosList(llistaAlumne);

        return llistaAlumneList;
    }

    @Override
    public List<AlumnoList> findAllAlumnoListDAW() {
        return AlumnoMapper.INSTANCE.alumnosToAlumnosList((List<AlumnoDb>) alumnoRepository.findAllAlumnoDbDAW());
    }

    @Override
    public List<AlumnoList> findAllAlumnoListDAM() {
        return AlumnoMapper.INSTANCE.alumnosToAlumnosList((List<AlumnoDb>) alumnoRepository.findAllAlumnoDbDAM());

    }

    @Override
    public List<AlumnoList> findAllAlumnosListCiclo(String ciclo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllAlumnosListCiclo'");
    }

    @Override
    public List<AlumnoList> findAllAlumnosListCicloPais(String ciclo, String pais) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllAlumnosListCicloPais'");
    }

    @Override
    public List<AlumnoList> findAllAlumnosListByDni(String dnl) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllAlumnosListByDni'");
    }

    @Override
    public List<AlumnoList> findAllAlumnosListByCiclo(String ciclo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllAlumnosListByCiclo'");
    }

    @Override
    public List<AlumnoList> findAllAlumnosListByHorario(String horario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllAlumnosListByHorario'");
    }

    @Override
    public List<AlumnoList> findAllAlumnosListByEdad(Integer edad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllAlumnosListByEdad'");
    }

    @Override
    public List<AlumnoList> findAlumnosListByCicloAndPais(String ciclo, String pais) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAlumnosListByCicloAndPais'");
    }

    @Override
    public List<AlumnoList> findAlumnosListByDistintoCiclo(String ciclo, String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAlumnosListByDistintoCiclo'");
    }

    @Override
    public List<AlumnoList> findAlumnosListByNombreIgnoreCase(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAlumnosListByNombreIgnoreCase'");
    }

    @Override
    public List<AlumnoList> findAlumnosListByCicloAndPaisAllIgnoreCase(String ciclo, String pais) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAlumnosListByCicloAndPaisAllIgnoreCase'");
    }

    @Override
    public List<AlumnoList> findAlumnosListByNombreLike(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAlumnosListByNombreLike'");
    }

    @Override
    public List<AlumnoList> findAllAlumnosListCicloOrderByCurso(String ciclo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllAlumnosListCicloOrderByCurso'");
    }

    @Override
    public List<AlumnoList> findByCicloOrderBy(String ciclo, Sort by) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCicloOrderBy'");
    }
}