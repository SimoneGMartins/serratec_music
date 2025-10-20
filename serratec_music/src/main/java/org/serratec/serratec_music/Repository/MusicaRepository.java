package org.serratec.serratec_music.Repository;

import org.serratec.serratec_music.domain.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> { }


