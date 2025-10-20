package org.serratec.serratec_music.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.Size;
import java.util.Set; 

@Entity
@Table(name = "artista")
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Size(min=3, max=100)
	@NotBlank
	@Column(name= "nome", length = 100, nullable = false )
	private String nome;


	@NotBlank
	@Size(max=50)
	@Column(name= "nacionalidade", length=50, nullable = false)
	private String nacionalidade;
	
	
	@ManyToMany(mappedBy = "artistas")
	private Set<Musica> musicas;

	public Artista(){
		super();
	}
	
	public Artista(Long id, String nome, String nacionalidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
	}

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNacionalidade() { 
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) { 
		this.nacionalidade = nacionalidade;
	}
	
	public Set<Musica> getMusicas() {
		return musicas;
	}
	public void setMusicas(Set<Musica> musicas) {
		this.musicas = musicas;
	}
}
	


