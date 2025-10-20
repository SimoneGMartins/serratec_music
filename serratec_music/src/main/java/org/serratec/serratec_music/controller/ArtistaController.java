package org.serratec.serratec_music.controller;

import java.util.List;

import org.serratec.serratec_music.Repository.ArtistaRepository;
import org.serratec.serratec_music.domain.Artista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Artistas", description = "Endpoints para gerenciamento de artistas.")
@RestController
@RequestMapping("/artistas") 
public class ArtistaController {

    @Autowired
    private ArtistaRepository artistaRepository;
    
    
	
    @GetMapping
    @Operation(summary = "Lista todos os artistas musicais")
    public ResponseEntity<List<Artista>> listarTodos(){
        return ResponseEntity.ok(artistaRepository.findAll());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Busca um artista pelo ID")
    public ResponseEntity<Artista> buscarPorId(@PathVariable Long id) {
       
        Artista artista = artistaRepository.findById(id)
            .orElseThrow();
        return ResponseEntity.ok(artista);
    }
    
    @PostMapping
    @Operation(summary = "Cria um novo artista")
    public ResponseEntity<Artista> criar(@RequestBody @Valid Artista artista) {
        Artista novoArtista = artistaRepository.save(artista);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoArtista);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um artista existente")
    public ResponseEntity<Artista> atualizar(@PathVariable Long id, @RequestBody @Valid Artista artista) {
       
        Artista artistaBanco = artistaRepository.findById(id)
            .orElseThrow();
        
       
        artistaBanco.setNome(artista.getNome());
        artistaBanco.setNacionalidade(artista.getNacionalidade()); 
        
        
        return ResponseEntity.ok(artistaRepository.save(artistaBanco));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um artista pelo ID")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!artistaRepository.existsById(id)) {
            
           
        }
        artistaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
	
	
	
	


