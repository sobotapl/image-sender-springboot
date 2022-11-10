package pl.sobota.imagesenderspringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sobota.imagesenderspringboot.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
