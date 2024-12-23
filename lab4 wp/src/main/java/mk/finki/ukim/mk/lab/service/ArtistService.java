package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService{
    List<Artist> listArtists();
    Optional<Artist> ArtistfindById(Long id);
}