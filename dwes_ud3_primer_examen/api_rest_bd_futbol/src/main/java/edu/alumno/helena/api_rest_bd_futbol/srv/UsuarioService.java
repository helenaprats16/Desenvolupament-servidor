package edu.alumno.helena.api_rest_bd_futbol.srv;

import java.util.Optional;

import edu.alumno.helena.api_rest_bd_futbol.model.dto.LoginUsuario;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.UsuarioInfo;
import io.micrometer.common.lang.NonNull;

public interface UsuarioService{
    public Optional<UsuarioInfo> getByNickname(@NonNull String nickname);
    public boolean existsByNickname(@NonNull String nickname);
    public boolean existsByEmail(@NonNull String email);
    public boolean comprobarLogin(@NonNull LoginUsuario loginUsuario);
}