package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {
    List<Album> albums;

    @PostConstruct
    public void init() {
        albums = new ArrayList<>();
        albums.add(new Album("Graduation", "Pop", "2007"));
        albums.add(new Album("Mercury", "Pop", "2022"));
        albums.add(new Album("R&G", "Hip Hop", "2004"));
        albums.add(new Album("Puci Kaci", "Grime", "2024"));
        albums.add(new Album("I want to die", "Metal", "2000"));
    }

    public List<Album> findAll() {
        return albums;
    }

    public Optional<Album> findById(long id) {
        return albums.stream().filter(album -> album.getId().equals(id)).findFirst();
    }
}
