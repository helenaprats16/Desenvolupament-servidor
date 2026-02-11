package edu.alumno.helena.api_rest_bd_futbol.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_futbol.model.db.UsuarioDb;
import edu.alumno.helena.api_rest_bd_futbol.security.entity.UsuarioPrincipal;
import edu.alumno.helena.api_rest_bd_futbol.security.service.UsuarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        //Método que debemos sobreescribir  (debe tener este nombre) de la interfaz UserDetailsService.
        //En nuestro caso buscamos por nickname en la BD y devolvemos un UsuarioPrincipal,
        //que es una implementación de la interfaz UserDetails.
        UsuarioDb usuario = usuarioService.getByNickname(nickname).get();
        return UsuarioPrincipal.build(usuario);
    }
}
