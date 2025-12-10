package edu.alumno.helena.api_rest_bd_futbol.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.helena.api_rest_bd_futbol.model.dto.LoginUsuario;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.UsuarioInfo;
import edu.alumno.helena.api_rest_bd_futbol.srv.UsuarioService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {
    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }


    @PostMapping("/login")
    public ResponseEntity<String> comprobarLogin(@Valid @RequestBody LoginUsuario loginUsuario) {
        if (usuarioService.comprobarLogin(loginUsuario)){            
            return ResponseEntity.ok("Login válido");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login invalido");
        }
    }
    

    @GetMapping("/byNickname/{nickname}")
    public ResponseEntity<UsuarioInfo> getUsuarioInfo(@PathVariable("nickname") String nickname) {
        Optional<UsuarioInfo> usuarioInfo = usuarioService.getByNickname(nickname);
        return usuarioInfo.map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    


}