package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.repository.AlbumRepository;
import mk.finki.ukim.mk.lab.repository.SongRepository;
import mk.finki.ukim.mk.lab.repository.jpaRepository.AlbumJpaRepository;
import mk.finki.ukim.mk.lab.repository.jpaRepository.SongJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/chooseAlbum")
public class ChooseAlbumController {

    public final SongJpaRepository songRepository;
    public final AlbumJpaRepository albumRepository;

    public ChooseAlbumController(SongJpaRepository songRepository, AlbumJpaRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @GetMapping
    public String chooseAlbum(Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        return "albumList";
    }

//    @PostMapping
//    public String filterByReleaseYear(@RequestParam Integer releaseYear, Model model) {
//        List<Album> albums = albumRepository.findAll().stream().filter(a -> Integer.parseInt(a.getReleaseYear()) >= releaseYear).toList();
//        model.addAttribute("albums", albums);
//        return "albumList";
//    }

    @GetMapping("/songsInAlbum")
    public String songsInAlbum(@RequestParam Long albumId, @RequestParam(required = false) Integer releaseYear, Model model) {
        List<Song> songs = songRepository.findAll().stream().filter(s -> {
            Optional<Album> albumce = albumRepository.findById(albumId);
            return albumce.filter(album -> s.getAlbum().equals(album)).isPresent();
        }).toList();

        if (releaseYear != null) {
            songs = songs.stream().filter(s -> s.getReleaseYear() >= releaseYear).toList();
        }

        model.addAttribute("songs", songs);
        return "showSongByAlbum";
    }
}
