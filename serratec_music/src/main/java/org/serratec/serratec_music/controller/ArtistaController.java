package org.serratec.serratec_music.controller;

import java.util.List;

import org.serratec.serratec_music.domain.Artista;
import org.serratec.serratec_music.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Artistas", description = "Endpoints para gerenciamento de artistas.")
@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaRepository artistaRepository;

    @Autowired
    public ArtistaController(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @GetMapping
    @Operation(summary = "Lista todos os artistas musicais")
    public ResponseEntity<List<Artista>> listarTodos() {
        List<Artista> artistas = artistaRepository.findAll();
        return ResponseEntity.ok(artistas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um artista pelo ID")
    public ResponseEntity<Artista> buscarPorId(@PathVariable Long id) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado com ID: " + id));
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
                .orElseThrow(() -> new RuntimeException("Artista não encontrado com ID: " + id));

        artistaBanco.setNome(artista.getNome());
        artistaBanco.setNacionalidade(artista.getNacionalidade());

        Artista atualizado = artistaRepository.save(artistaBanco);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um artista pelo ID")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        artistaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


	
	
	


