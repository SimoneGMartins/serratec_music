package org.serratec.serratec_music.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotNull
    private Integer minutos;

    @Enumerated(EnumType.STRING)
    private GeneroMusical genero;

    @ManyToMany(mappedBy = "musicas")
    @JsonBackReference
    private Set<Playlist> playlists;

    @ManyToMany
    @JoinTable(
        name = "musica_artista",
        joinColumns = @JoinColumn(name = "musica_id"),
        inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    @JsonManagedReference
    private Set<Artista> artistas;

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Integer getMinutos() { return minutos; }
    public void setMinutos(Integer minutos) { this.minutos = minutos; }

    public GeneroMusical getGenero() { return genero; }
    public void setGenero(GeneroMusical genero) { this.genero = genero; }

    public Set<Playlist> getPlaylists() { return playlists; }
    public void setPlaylists(Set<Playlist> playlists) { this.playlists = playlists; }

    public Set<Artista> getArtistas() { return artistas; }
    public void setArtistas(Set<Artista> artistas) { this.artistas = artistas; }
}
