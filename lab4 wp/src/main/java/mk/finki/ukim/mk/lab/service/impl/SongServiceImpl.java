package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.repository.jpaRepository.SongJpaRepository;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongJpaRepository songRepository;

    public SongServiceImpl(SongJpaRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public void addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        songRepository.save(song);
    }

    @Override
    public Optional<Song> findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void addSong(Song song) {
        songRepository.save(song);
//        songRepository.addSong(song);
    }

    @Override
    public void editSong(Long id, String trackId, String title, String genre, Integer releaseYear, Album album) {
        Song song = songRepository.findById(id).orElse(null);
        if (song == null) return;

        if (trackId != null && !trackId.isEmpty()) song.setTrackId(trackId);
        if (title != null && !title.isEmpty()) song.setTitle(title);
        if (genre != null && !genre.isEmpty()) song.setGenre(genre);
        if (releaseYear != null && releaseYear > 0) song.setReleaseYear(releaseYear);
        if (album != null) song.setAlbum(album);

        songRepository.save(song);
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }
}
