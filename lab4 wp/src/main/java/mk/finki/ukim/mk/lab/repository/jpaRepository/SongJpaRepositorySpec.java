//package mk.finki.ukim.mk.lab.repository.jpaRepository;
//
//
//import mk.finki.ukim.mk.lab.model.Album;
//import mk.finki.ukim.mk.lab.model.Song;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//class SongSpecification {
//
//    public static Specification<Song> releaseInYear(Integer releaseYear) {
//        return ((root, query, criteriaBuilder) ->
//                criteriaBuilder.greaterThan(root.get("releaseYear"), releaseYear));
//    }
//}
//
////@Repository
//public interface SongJpaRepositorySpec extends JpaRepository<Album, Long> {
//    List<Song> findAll(SongSpecification spec);
//}
