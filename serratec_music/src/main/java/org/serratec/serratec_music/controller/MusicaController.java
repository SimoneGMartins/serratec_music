package org.serratec.serratec_music.controller;

import java.util.List;

import org.serratec.serratec_music.domain.Musica;
import org.serratec.serratec_music.exception.ResourceNotFoundException;
import org.serratec.serratec_music.repository.ArtistaRepository;
import org.serratec.serratec_music.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/musicas")
public class MusicaController {


@Autowired
private MusicaRepository musicaRepository;


@Autowired
private ArtistaRepository artistaRepository;


@Operation(summary = "CRUD de músicas. Ao criar, informe ids de artistas para associar (opcional).")
@PostMapping
public ResponseEntity<Musica> criar(@Valid @RequestBody Musica musica) {

if (musica.getArtistas() != null && !musica.getArtistas().isEmpty()) {

musica.getArtistas().forEach(a -> {
if (a.getId() != null && !artistaRepository.existsById(a.getId())) {
throw new ResourceNotFoundException("Artista com id " + a.getId() + " não encontrado");
}
});
}
Musica salvo = musicaRepository.save(musica);
return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
}


@GetMapping
public List<Musica> listar() { return musicaRepository.findAll(); }


@GetMapping("/{id}")
public Musica buscar(@PathVariable Long id) {
return musicaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Música não encontrada"));
}


@PutMapping("/{id}")
public Musica atualizar(@PathVariable Long id, @Valid @RequestBody Musica input) {
Musica m = musicaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Música não encontrada"));
m.setTitulo(input.getTitulo());
m.setMinutos(input.getMinutos());
m.setGenero(input.getGenero());

if (input.getArtistas() != null) m.setArtistas(input.getArtistas());
return musicaRepository.save(m);
}


@DeleteMapping("/{id}")
public ResponseEntity<Void> deletar(@PathVariable Long id) {
musicaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Música não encontrada"));
musicaRepository.deleteById(id);
return ResponseEntity.noContent().build();
}
}


