package com.usuario.prueba.rest;

import com.usuario.prueba.model.Usuario;
import com.usuario.prueba.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping ("/usuarios/")

public class UsuarioRest {

    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    private ResponseEntity<List<Usuario>>  getAllUsuarios(){
        return ResponseEntity.ok(usuarioService.findAll());
    }
    @PostMapping
    private ResponseEntity<Usuario> saveUsuario (Usuario usuario){

        try {
            Usuario usuario1 = usuarioService.save(usuario);
            return ResponseEntity.created(new URI("/usuarios/"+usuario1.getId())).body(usuario1);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping(value = "delete/{id}")
    private ResponseEntity<Boolean> deleteUsuario(@PathVariable ("id") Long id ){
        usuarioService.deleteById(id);
        return ResponseEntity.ok(!(usuarioService.findById(id)!=null));
    }
}
