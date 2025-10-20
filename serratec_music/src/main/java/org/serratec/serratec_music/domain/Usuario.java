package org.serratec.serratec_music.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name= "usuario")

public class Usuario {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank
	    private String nome;

	    @Email
	    @NotBlank
	    private String email;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "perfil_id")
	    private Perfil perfil;

	    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	    private List<Playlist> playlists = new ArrayList<>();

	    public Usuario() { }

	    public Usuario(Long id, String nome, String email, Perfil perfil) {
	        this.id = id;
	        this.nome = nome;
	        this.email = email;
	        this.perfil = perfil;
	    }

	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getNome() { return nome; }
	    public void setNome(String nome) { this.nome = nome; }

	    public String getEmail() { return email; }
	    public void setEmail(String email) { this.email = email; }

	    public Perfil getPerfil() { return perfil; }
	    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

	    public List<Playlist> getPlaylists() { return playlists; }
	    public void setPlaylists(List<Playlist> playlists) { this.playlists = playlists; }
	}


