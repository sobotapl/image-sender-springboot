package pl.sobota.imagesenderspringboot;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sobota.imagesenderspringboot.repository.UserAppRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserAppRepository userAppRepository;

    public UserDetailsServiceImpl(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepository.;
    }
}
