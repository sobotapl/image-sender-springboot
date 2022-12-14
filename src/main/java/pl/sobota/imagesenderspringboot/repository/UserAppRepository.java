package pl.sobota.imagesenderspringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sobota.imagesenderspringboot.model.UserApp;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {


    UserApp findByUsername(String username);



}
