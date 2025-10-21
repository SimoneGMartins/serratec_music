package org.serratec.serratec_music.controller;

import java.util.List;
import java.util.Set;

import org.serratec.serratec_music.domain.Musica;
import org.serratec.serratec_music.domain.Playlist;
import org.serratec.serratec_music.domain.Usuario;
import org.serratec.serratec_music.exception.ResourceNotFoundException;
import org.serratec.serratec_music.repository.MusicaRepository;
import org.serratec.serratec_music.repository.PlaylistRepository;
import org.serratec.serratec_music.repository.UsuarioRepository;
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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    // ------------------- POST -------------------
    @Operation(summary = "Cria playlist associada a um usuário existente (informe usuario.id no body)")
    @PostMapping
    public ResponseEntity<Playlist> criar(@Valid @RequestBody Playlist playlist) {
        if (playlist.getUsuario() == null || playlist.getUsuario().getId() == null) {
            throw new IllegalArgumentException("Informe o id do usuário ao criar a playlist");
        }

        Usuario u = usuarioRepository.findById(playlist.getUsuario().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        playlist.setUsuario(u);
        Playlist salvo = playlistRepository.save(playlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // ------------------- GET -------------------
    @GetMapping
    public List<Playlist> listar() {
        return playlistRepository.findAll();
    }

    @GetMapping("/{id}")
    public Playlist buscar(@PathVariable Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist não encontrada"));
    }

    // ------------------- PUT -------------------
    @PutMapping("/{id}")
    public Playlist atualizar(@PathVariable Long id, @Valid @RequestBody Playlist input) {
        Playlist p = playlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist não encontrada"));

        p.setNome(input.getNome());
        p.setDescricao(input.getDescricao());

        if (input.getMusicas() != null) {
            List<Long> ids = input.getMusicas().stream().map(Musica::getId).toList();
            List<Musica> musicas = musicaRepository.findAllById(ids);

            if (musicas.size() != ids.size()) {
                throw new ResourceNotFoundException("Uma ou mais músicas informadas não foram encontradas");
            }

            p.setMusicas(Set.copyOf(musicas));
        }

        return playlistRepository.save(p);
    }

    // ------------------- DELETE -------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        playlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist não encontrada"));
        playlistRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}




