package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.repository.jpaRepository.AlbumJpaRepository;
import mk.finki.ukim.mk.lab.repository.jpaRepository.ArtistJpaRepository;
import mk.finki.ukim.mk.lab.repository.jpaRepository.SongJpaRepository;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class DataHolder {
    public static List<Artist> artistList;
    public static List<Song> songs;
    public static List<Album> albums;

    private final AlbumJpaRepository albumRepository;
    private final ArtistJpaRepository artistRepository;
    private final SongJpaRepository songRepository;

    public DataHolder(AlbumJpaRepository albumRepository, ArtistJpaRepository artistRepository, SongJpaRepository songRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }

    @PostConstruct
    public void init() {
        artistList = new ArrayList<>();
        artistList.add(new Artist("Michael", "Jackson", "hehe"));
        artistList.add(new Artist("Dragana", "Petrova", "heh"));
        artistList.add(new Artist("Ivana", "Petrova", "hehe:P"));
        artistList.add(new Artist("Ilija", "Aleksandrovski", "hehe:)"));
        artistList.add(new Artist("Miroslav", "James", "hehue"));

        if (this.artistRepository.count() == 0) {
            this.artistRepository.saveAll(artistList);
        }


        albums=new ArrayList<>();
        albums.add(new Album("Album 1", "Rock", "2020"));
        albums.add(new Album("Album 2", "Pop", "2018"));
        albums.add(new Album("Album 3", "Jazz", "2019"));
        albums.add(new Album("Album 4", "Classical", "2021"));
        albums.add(new Album("Album 5", "Electronic", "2022"));

        if (this.albumRepository.count() == 0) {
            this.albumRepository.saveAll(albums);
        }

        songs = new ArrayList<>();
        List<Artist> tmp = artistList.stream().filter(a -> a.getFirstName().startsWith("M")).collect(Collectors.toList());
        songs.add(new Song("1", "zdr", "rock", 2017, tmp, albums.get(0)));
        songs.add(new Song("2", "kakosi", "rock", 2019, artistList.stream().filter(a -> a.getFirstName().startsWith("I")).collect(Collectors.toList()), albums.get(0)));
        songs.add(new Song("3", "ne znam", "pop", 2014, artistList.stream().filter(a -> a.getFirstName().startsWith("D")).collect(Collectors.toList()), albums.get(1)));

//        Random random = new Random();
//        songs.forEach(song -> {
//            int randomIndex = random.nextInt(albumRepository.findAll().size());
//            Album randomAlbum = albumRepository.findAll().get(randomIndex);
//            song.setAlbum(randomAlbum);
//        });


        if (this.songRepository.count() == 0) {
            this.songRepository.saveAll(songs);
        }
    }

}

