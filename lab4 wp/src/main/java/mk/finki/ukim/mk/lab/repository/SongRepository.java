package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {
    List<Song> songs;

    @PostConstruct
    public void init() {
        songs = new ArrayList<Song>();

        List<Artist> artists = new ArrayList<Artist>();
        artists.add(new Artist((long) 6, "Trajko", "Trajkoski", "Pasino Ruvci!"));
        artists.add(new Artist((long) 7, "Cunko", "Petreski", "Sekirci!"));
        artists.add(new Artist((long) 1, "Nikola", "Jagurinoski", "Aren sum, ti?"));

        Album album1 = new Album("Graduation", "Pop", "2007");
        Album album2 = new Album("Mercury", "Pop", "2022");
        Album album3 = new Album("R&G", "Hip Hop", "2004");

        songs.add(new Song("1", "Pictures", "Pop", 1997, new ArrayList<>(), album1));
        songs.add(new Song("2", "Tracking Time", "Rock", 1989, artists, album1));
        songs.add(new Song("3", "Listen to me...", "Pop", 2001, new ArrayList<>(), album2));
        songs.add(new Song("4", "Let me cook", "Hip hop", 2003, artists, album2));
        songs.add(new Song("5", "He is cooking", "Black Metal", 2021, new ArrayList<>(), album3));
    }

    public List<Song> findAll() {
        return songs;
    }

    public Optional<Song> findByTrackId(String trackId) {
        return songs.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        return artist;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public Optional<Song> findById(Long id) {
        return songs.stream()
                .filter(song -> song.getId().equals(id))
                .findFirst();
    }

    public boolean editSong(Song song, String trackId, String title, String genre, Integer releaseYear, Album album) {
        if (song == null) {
            return false;
        }

        if (trackId != null && !trackId.isEmpty()) song.setTrackId(trackId);
        if (title != null && !title.isEmpty()) song.setTitle(title);
        if (genre != null && !genre.isEmpty()) song.setGenre(genre);
        if (releaseYear != null && releaseYear > 0) song.setReleaseYear(releaseYear);
        if (album != null) song.setAlbum(album);

        return true;
    }

    public void deleteSong(Long id) {
        Optional<Song> songce = findById(id);
        songce.ifPresent(song -> songs.remove(song));
    }
}
