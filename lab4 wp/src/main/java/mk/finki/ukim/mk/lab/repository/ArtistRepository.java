package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {

    List<Artist> artistList;

    @PostConstruct
    public void init() {
        this.artistList = new ArrayList<>();
        artistList.add(new Artist((long) 1, "Nikola", "Jagurinoski", "Aren sum, ti?"));
        artistList.add(new Artist((long) 2, "Alokin", "Nirugajski", "Ne sum aren!"));
        artistList.add(new Artist((long) 3, "Petar", "Nastoski", "Eden i e..."));
        artistList.add(new Artist((long) 4, "Wagwan", "Koleski", "Edinstven i e!"));
        artistList.add(new Artist((long) 5, "Dzole", "Dzoleski", "Ne sum odavde!"));
    }

    public List<Artist> findAll() {
        return artistList;
    }

    public Optional<Artist> findById(Long id) {
        return artistList.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }
}
