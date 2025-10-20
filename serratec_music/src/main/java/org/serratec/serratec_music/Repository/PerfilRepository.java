package org.serratec.serratec_music.Repository;

import org.serratec.serratec_music.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
