package edu.alumno.helena.api_rest_bd_futbol.srv.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_futbol.model.db.UsuarioDb;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.LoginUsuario;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.UsuarioInfo;
import edu.alumno.helena.api_rest_bd_futbol.repository.UsuarioRepository;
import edu.alumno.helena.api_rest_bd_futbol.srv.UsuarioService;
import edu.alumno.helena.api_rest_bd_futbol.srv.mapper.UsuarioMapper;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<UsuarioInfo> getByNickname(String nickname) {
        
        Optional<UsuarioDb> usuarioDb=usuarioRepository.findByNickname(nickname);
        if (usuarioDb.isPresent()){
            UsuarioDb usuarioNicknameDb = usuarioDb.get();
            return Optional.of(UsuarioMapper.INSTANCE.usuarioDbToUsuarioInfo(usuarioNicknameDb));//Pasar de UsuarioDb a UsuarioInfo
        }else 
            return Optional.empty();
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return usuarioRepository.existsByNickname(nickname);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);

    }

    @Override
    public boolean comprobarLogin(LoginUsuario loginUsuario) {
        Optional<UsuarioDb> usuarioDb = usuarioRepository.findByNickname(loginUsuario.getNickname());       

        if (usuarioDb.isPresent()) {
            UsuarioDb usuari = usuarioDb.get();
            return usuari.getPassword().equals(loginUsuario.getPassword());
        }else{
            return false;
        }
    }




    




}