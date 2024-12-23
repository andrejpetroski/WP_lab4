package mk.finki.ukim.mk.lab.repository.jpaRepository;

import mk.finki.ukim.mk.lab.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistJpaRepository extends JpaRepository<Artist, Long> {
}
