package org.serratec.serratec_music.Repository;

import org.serratec.serratec_music.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaylistRepository extends JpaRepository<Playlist, Long> { }


