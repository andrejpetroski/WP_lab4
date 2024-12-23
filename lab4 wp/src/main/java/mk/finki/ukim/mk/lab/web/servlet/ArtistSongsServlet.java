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
import java.util.List;

@WebServlet(name = "ArtistSongsServlet", urlPatterns = "/artistSongs")
public class ArtistSongsServlet extends HttpServlet {

    public SpringTemplateEngine springTemplateEngine;
    public ArtistService artistService;
    public SongService songService;

    public ArtistSongsServlet(SpringTemplateEngine springTemplateEngine, ArtistService artistService, SongService songService) {
        this.springTemplateEngine = springTemplateEngine;
        this.artistService = artistService;
        this.songService = songService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(exchange);

        String artistId = req.getParameter("artistId");
        Artist artist = artistService.ArtistfindById(Long.valueOf(artistId)).get();

        List<Song> listSongs = songService.listSongs();
        List<Song> songs = listSongs.stream()
                        .filter(s -> s.getPerformers().contains(artist))
                                .toList();

        context.setVariable("artist", artist);
        context.setVariable("songs", songs);

        springTemplateEngine.process("artistSongs.html", context, resp.getWriter());
    }

}
