package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.AlbumService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        List<Song> songs = songService.listSongs();

        model.addAttribute("songs", songs);

        return "listSongs";
    }

    //  http://localhost:8080/song/add?title=TITLICA&trackId=123&genre=Rock&releaseYear=2021&albumId=456
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddSongPage(Model model) {
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("boolAdd", true);

        return "add-song";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveSong(String title,
                           String trackId,
                           String genre,
                           Integer releaseYear,
                           Long albumId,
                           Model model) {
        Album album = albumService.findById(albumId).orElse(null);

        Song songce = null;
        try {
            songce = new Song(trackId, title, genre, releaseYear, new ArrayList<>(), album);
        } catch (RuntimeException e) {
            model.addAttribute("boolAdd", true);
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("albums", albumService.findAll());
            return "add-song";
        }

        songService.addSong(songce);

        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{songId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditSongForm(@PathVariable Long songId,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        Optional<Song> song = songService.findById(songId);
        if (song.isEmpty()) {
            redirectAttributes.addFlashAttribute("hasError", true);
            redirectAttributes.addFlashAttribute("error", "Song not found");
            return "redirect:/songs";
        }


        model.addAttribute("boolEdit", true);
        model.addAttribute("songId", songId);
        model.addAttribute("song", song.get());
        model.addAttribute("albums", albumService.findAll());

        return "add-song";
    }

    @PostMapping("/edit/{songId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editSong(@PathVariable Long songId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String trackId,
                           @RequestParam(required = false) String genre,
                           @RequestParam(required = false) Integer releaseYear,
                           @RequestParam(required = false) Long albumId,
                           Model model) {
        Album album = null;
        if (albumId != null) {
            album = albumService.findById(albumId).orElse(null);
        }
        songService.editSong(songId, trackId, title, genre, releaseYear, album);

        return "redirect:/songs";
    }


    @GetMapping("/delete/{songId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSong(@PathVariable Long songId) {
        songService.deleteSong(songId);

        return "redirect:/songs";
    }
}
