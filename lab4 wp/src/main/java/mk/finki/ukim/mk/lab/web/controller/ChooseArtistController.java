package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Artist;
import org.springframework.ui.Model;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chooseArtist")
public class ChooseArtistController {

    public final ArtistService artistService;
    public final SongService songService;

    public ChooseArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String getArtists(Model model) {
        model.addAttribute("artists", artistService.listArtists());
        return "chooseArtist";
    }

    @PostMapping
    public String searchArtist(@RequestParam String searchBy, Model model) {
        List<Artist> list = artistService.listArtists().stream()
                .filter(a -> {
                    String fullname = a.getFirstName() + " " + a.getLastName();
                    return fullname.toLowerCase().contains(searchBy.toLowerCase());
                }).toList();

        model.addAttribute("artists", list);
        return "chooseArtist";
    }
}
