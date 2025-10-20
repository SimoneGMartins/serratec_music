package org.serratec.serratec_music.Repository;

import org.serratec.serratec_music.domain.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ArtistaRepository extends JpaRepository<Artista, Long>{

}
