package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

//@WebServlet(name = "ArtistShowSongsServlet", urlPatterns = "/chooseArtist")
public class ArtistShowSongsServlet extends HttpServlet {

    public SpringTemplateEngine springTemplateEngine;
    public ArtistService artistService;
    public SongService songService;

    public ArtistShowSongsServlet(SpringTemplateEngine springTemplateEngine, ArtistService artistService, SongService songService) {
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

        context.setVariable("artists", artistService.listArtists());

        springTemplateEngine.process("chooseArtist.html", context, resp.getWriter());
    }
}
