package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.finki.ukim.mk.lab.model.exceptions.MissingArgumentForSongException;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String trackId;
    String title;
    String genre;
    int releaseYear;

    // TODO check this
    @OneToMany
    private List<Artist> performers;

    @ManyToOne
    private Album album;

    public Song(String trackId, String title, String genre, int releaseYear, List<Artist> performers) {
//        this.id = (long) (Math.random() * 1000);
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = performers;
    }

    public Song(String trackId, String title, String genre, Integer releaseYear, List<Artist> performers, Album album) {
        if (title == null || title.isEmpty()
                || trackId == null || trackId.isEmpty()
                || genre == null || genre.isEmpty()
                || releaseYear == null || releaseYear < 0
                || album == null) {
            throw new MissingArgumentForSongException();
        }

//        this.id = (long) (Math.random() * 1000);
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.album = album;
        this.performers = performers;
    }

    @Override
    public String toString() {
        return "Song{" +
                "trackId='" + trackId + '\'' +
                "ID='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
