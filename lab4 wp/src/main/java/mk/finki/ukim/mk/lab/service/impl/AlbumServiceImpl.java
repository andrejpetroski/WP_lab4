package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.repository.AlbumRepository;
import mk.finki.ukim.mk.lab.repository.jpaRepository.AlbumJpaRepository;
import mk.finki.ukim.mk.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumJpaRepository albumRepository;

    public AlbumServiceImpl(AlbumJpaRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }
}
