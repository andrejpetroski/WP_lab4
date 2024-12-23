package mk.finki.ukim.mk.lab.repository.jpaRepository;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongJpaRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByTrackId(String trackId);
    List<Song> findAllByAlbum_Id(Long albumId);
    void deleteById(Long id);
//    void updateSongById(Long id, Song song);
}
