package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Optional;

//@WebServlet(name = "songDetails", urlPatterns = "/songDetails")
public class songDetailsServlet extends HttpServlet {

    public SpringTemplateEngine springTemplateEngine;
    public SongService songService;
    public ArtistService artistService;

    public songDetailsServlet(SpringTemplateEngine springTemplateEngine, SongService songService, ArtistService artistService) {
        this.springTemplateEngine = springTemplateEngine;
        this.songService = songService;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(exchange);

        String trackId = req.getParameter("trackId");
        String artistId = req.getParameter("artistId");

        Song song = songService.findByTrackId(trackId).orElse(null);
        Artist artist = artistService.ArtistfindById(Long.valueOf(artistId)).orElse(null);

        songService.addArtistToSong(artist, song);

        context.setVariable("song", song);

        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
