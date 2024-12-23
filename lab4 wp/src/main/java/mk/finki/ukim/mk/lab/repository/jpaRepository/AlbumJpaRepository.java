package mk.finki.ukim.mk.lab.repository.jpaRepository;

import mk.finki.ukim.mk.lab.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumJpaRepository extends JpaRepository<Album, Long> {
//    public Album findById(Long albumId);
}
