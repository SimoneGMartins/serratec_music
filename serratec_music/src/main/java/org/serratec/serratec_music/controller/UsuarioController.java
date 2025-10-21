package org.serratec.serratec_music.controller;

import org.serratec.serratec_music.domain.Usuario;
import org.serratec.serratec_music.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários e perfis.")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

   

  
    @PostMapping
    @Operation(summary = "Cria um novo usuário e seu perfil em uma única requisição.")
    public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario) {
       
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
}
